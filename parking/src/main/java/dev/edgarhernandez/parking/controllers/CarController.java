package dev.edgarhernandez.parking.controllers;

import dev.edgarhernandez.parking.dto.*;
import dev.edgarhernandez.parking.entities.OfficialCar;
import dev.edgarhernandez.parking.entities.ResidentCar;
import dev.edgarhernandez.parking.exceptions.ResourceNotFoundException;
import dev.edgarhernandez.parking.mapper.OfficialCarMapper;
import dev.edgarhernandez.parking.service.*;
import dev.edgarhernandez.parking.utilities.ThreeColumnData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/parking")
public class CarController {
    private CarService carService;

    private OfficialCarService officialCarService;
    private ResidentCarService residentCarService;
    private NoResidentCarService noResidentCarService;
    private StaySevice staySevice;

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        carDTO.setInRegister(LocalDateTime.now());
        CarDTO savedCar = carService.createCar(carDTO);
        return new ResponseEntity<CarDTO>(savedCar, HttpStatus.CREATED);
    }

    @PostMapping("/createOfficial")
    public ResponseEntity<OfficialCarDTO> createOfficialCar(@RequestBody OfficialCarDTO officialCarDTO) {
        officialCarDTO.setInRegister(LocalDateTime.now());
        if (exists(officialCarDTO.getPlate())){
            return new ResponseEntity<OfficialCarDTO>(HttpStatus.BAD_REQUEST);
        }else {
            OfficialCarDTO savedCar = officialCarService.createOfficial(officialCarDTO);
            return new ResponseEntity<OfficialCarDTO>(savedCar, HttpStatus.CREATED);
        }
    }

    @PostMapping("/createResident")
    public ResponseEntity<ResidentCarDTO> createNoResidentCar(@RequestBody ResidentCarDTO residentCarDTO) {
            residentCarDTO.setInRegister(LocalDateTime.now());
            if (exists(residentCarDTO.getPlate())){
                return new ResponseEntity<ResidentCarDTO>(HttpStatus.BAD_REQUEST);
            }else {
                ResidentCarDTO savedCar = residentCarService.createResident(residentCarDTO);
                return new ResponseEntity<ResidentCarDTO>(savedCar, HttpStatus.CREATED);
            }
    }

    @PostMapping("/createNoResident")
    public ResponseEntity<NoResidentCarDTO> createNoResidentCar(@RequestBody NoResidentCarDTO noResidentCarDTO) {
        noResidentCarDTO.setInRegister(LocalDateTime.now());
        if (!exists(noResidentCarDTO.getPlate())) {
            NoResidentCarDTO savedCar = noResidentCarService.createNoResident(noResidentCarDTO);
            return new ResponseEntity<NoResidentCarDTO>(savedCar, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<NoResidentCarDTO>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("{plate}")
    public  ResponseEntity<CarDTO> getCarByPlate(@PathVariable("plate") String plate){
        CarDTO carDto = carService.getCarByPlate(plate);
        return ResponseEntity.ok(carDto);
    }

    @GetMapping("importe/{plate}")
    public ResponseEntity<Float> getAmountToPay(@PathVariable("plate") String plate){
        NoResidentCarDTO noResidentCarDTO = noResidentCarService.getNoResidentByPlate(plate);
        return ResponseEntity.ok(noResidentCarDTO.getAmount());
    }

    @GetMapping("/generarReporte/{reportName}")
    public void generateJson(@PathVariable("reportName") String reportName, HttpServletResponse response) {

        List<ThreeColumnData> data = getData();
        String filePath = reportName + ".txt";
        residentCarService.generateFile(data, filePath);
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             OutputStream outputStream = response.getOutputStream()) {

            String line;
            while ((line = reader.readLine()) != null) {
                outputStream.write(line.getBytes());
                outputStream.write("\n".getBytes());
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file reading failure
        }
    }

    @PutMapping("salida/{plate1}")
    public ResponseEntity<String> updateOutRegister(@PathVariable("plate1") String plate){
        String message = "";
        if (checkEntityType(plate).equals("NR")){
            calculateAmount(plate);
            message = "Se actualizó el auto residente con éxito";
            return ResponseEntity.ok(message);
        } else if (checkEntityType(plate).equals("O")) {
            saveStay(plate);
            message = "Se actualizó el auto residente con éxito";
            return ResponseEntity.ok(message);
        } else if (checkEntityType(plate).equals("R")) {
            acumulateTime(plate);
            calculateAmountPerMonth(plate);
            message = "Se actualizó el auto residente con éxito";
            return ResponseEntity.ok(message);
        }else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("comienzaMes")
    public ResponseEntity<String> deleteAllStays(){
        try {
            staySevice.deleteAll();
            residentCarService.updateResidentCarAcm();
            return ResponseEntity.ok("Registros de estancias borrados");
        }catch (Exception e){
            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }
    }


    @PutMapping("entrada/{plate2}")
    public ResponseEntity<CarDTO> updateInRegister(@PathVariable("plate2") String plate){
        CarDTO carDTO = carService.updateCarIn(plate);
        return ResponseEntity.ok(carDTO);
    }

    private void saveStay(String plate){
        OfficialCarDTO officialCarDto = officialCarService.updateCarOut(plate);
        StayDTO stayDTO = new StayDTO();
        stayDTO.setStartDateTime(officialCarDto.getInRegister());
        stayDTO.setEndDateTime(officialCarDto.getOutRegister());
        OfficialCar officialCar = OfficialCarMapper.mapToOfficialCar(officialCarDto);
        stayDTO.setOfficialCar(officialCar);

        StayDTO savedStayDto = staySevice.createStay(stayDTO);
    }
    private void calculateAmount(String plate){
        NoResidentCarDTO savedResident = noResidentCarService.updateCarOut(plate);
    }

    private void calculateAmountPerMonth(String plate){
        ResidentCarDTO savedResident = residentCarService.updateAmountPerMonth(plate);
    }

    public void acumulateTime(String plate){
        ResidentCarDTO savedResident = residentCarService.updateCarOut(plate);
    }


    public boolean exists(String plate){
        try {
            CarDTO carDTO = carService.getCarByPlate(plate);
            return true;
        }catch(ResourceNotFoundException e){
            return false;
        }
    }

    public String checkEntityType(String plate) {
        try {
            NoResidentCarDTO noResidentCarDTO = noResidentCarService.getNoResidentByPlate(plate);
            return "NR";
        } catch (ResourceNotFoundException e) {
            e.getMessage();
        }

        try {
            ResidentCarDTO residentCarDTO = residentCarService.getResidentByPlate(plate);
            return "R";
        } catch (ResourceNotFoundException e) {
            e.getMessage();
        }

        try {
            OfficialCarDTO officialCarDTO = officialCarService.getOfficialByPlate(plate);
            return "O";
        } catch (ResourceNotFoundException e) {
            e.getMessage();
        }
        return "";
    }

    private List<ThreeColumnData> getData(){
        List<ThreeColumnData> data = new ArrayList<>();
        List<ResidentCar> dataCollection = residentCarService.getAllResidents();
        for (ResidentCar residentCar : dataCollection){
            data.add(new ThreeColumnData(residentCar.getPlate(), residentCar.getAcumHours(), residentCar.getAmountPerMonth().toString()));
        }
        return data;
    }
}
