package dev.edgarhernandez.parking.service.implementation;

import dev.edgarhernandez.parking.dto.ResidentCarDTO;
import dev.edgarhernandez.parking.entities.ResidentCar;
import dev.edgarhernandez.parking.exceptions.ResourceNotFoundException;
import dev.edgarhernandez.parking.mapper.ResidentCarMapper;
import dev.edgarhernandez.parking.repositories.ResidentCarRepository;
import dev.edgarhernandez.parking.service.ResidentCarService;
import dev.edgarhernandez.parking.utilities.ThreeColumnData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ResidentCarServiceImpl implements ResidentCarService {
    private ResidentCarRepository residentCarRepository;
    @Override
    public ResidentCarDTO createResident(ResidentCarDTO residentCarDTO) {
        try {
            ResidentCar residentCar = ResidentCarMapper.mapToResidentCar(residentCarDTO);
            ResidentCar residentSavedCar = residentCarRepository.save(residentCar);
            return ResidentCarMapper.mapToResidentCarDto(residentSavedCar);
        }catch (Exception e){
            e.getMessage();
            return null;
        }

    }

    @Override
    public ResidentCarDTO getResidentByPlate(String plate) {
        ResidentCar residentCarDTO = residentCarRepository.findByPlate(plate);
        if (residentCarDTO == null){
            throw new ResourceNotFoundException("El auto con la placa: "+plate+" no se encontró");
        }
        return ResidentCarMapper.mapToResidentCarDto(residentCarDTO);
    }

    @Override
    public ResidentCarDTO updateCarOut(String carPlate) {
        ResidentCar residentCar = residentCarRepository.findByPlate(carPlate);
        residentCar.setOutRegister(LocalDateTime.now());
        Long hours, minutes;
        String acumTime;
        Duration duration = Duration.between(residentCar.getInRegister(), residentCar.getOutRegister());
        hours = duration.toHours();
        minutes = duration.minusHours(hours).toMinutes();
        acumTime = hours + ":" + minutes;
        residentCar.setAcumHours(acumTime);
        ResidentCar updatedCarObj;
        if (residentCar != null) {
            updatedCarObj = residentCarRepository.save(residentCar);
        } else {
            throw new ResourceNotFoundException("El auto con la placa: " + carPlate + " no se encontró");
        }
        return ResidentCarMapper.mapToResidentCarDto(updatedCarObj);
    }

    @Override
    public List<ResidentCar> getAllResidents() {
        return residentCarRepository.findAll();
    }

    @Override
    public void updateResidentCarAcm() {
        List<ResidentCar> residentCars = residentCarRepository.findAll();
        for (ResidentCar residentCar : residentCars){
            residentCar.setAcumHours("00:00");
            residentCarRepository.save(residentCar);
        }
    }

    @Override
    public void generateFile(List<ThreeColumnData> data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Define column widths
            int column1Width = 15; // Example width for Column1
            int column2Width = 30; // Example width for Column2
            int column3Width = 20; // Example width for Column3

            // Write column headers with padding
            writer.write(String.format("%-" + column1Width + "s", "Núm. Placa"));
            writer.write(String.format("%-" + column2Width + "s", "Tiempo estacionado (min.)"));
            writer.write(String.format("%-" + column3Width + "s", "Cantidad a pagar"));
            writer.newLine();

            // Write data rows with padding
            for (ThreeColumnData row : data) {
                writer.write(String.format("%-" + column1Width + "s", row.getColumn1()));
                writer.write(String.format("%-" + column2Width + "s", row.getColumn2()));
                writer.write(String.format("%-" + column3Width + "s", row.getColumn3()));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResidentCarDTO updateAmountPerMonth(String plate) {
        ResidentCar residentCar = residentCarRepository.findByPlate(plate);
        String[] parts = residentCar.getAcumHours().split(":");
        Long hours = Long.parseLong(parts[0]);
        Long minutes = Long.parseLong(parts[1]);

        Long duration = hours*60+minutes;

        residentCar.setAmountPerMonth(Float.valueOf((float) (duration*0.5)));

        ResidentCar residentCarObj = residentCarRepository.save(residentCar);

        return ResidentCarMapper.mapToResidentCarDto(residentCarObj);
    }
}
