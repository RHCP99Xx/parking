package dev.edgarhernandez.parking.service.implementation;

import dev.edgarhernandez.parking.dto.NoResidentCarDTO;
import dev.edgarhernandez.parking.entities.Car;
import dev.edgarhernandez.parking.entities.NoResidentCar;
import dev.edgarhernandez.parking.entities.ResidentCar;
import dev.edgarhernandez.parking.exceptions.ResourceNotFoundException;
import dev.edgarhernandez.parking.mapper.CarMapper;
import dev.edgarhernandez.parking.mapper.NoResidentCarMapper;
import dev.edgarhernandez.parking.repositories.NoResidentCarRepository;
import dev.edgarhernandez.parking.service.NoResidentCarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class NoResidentCarImpl implements NoResidentCarService {
    private NoResidentCarRepository noResidentCarRepository;
    @Override
    public NoResidentCarDTO createNoResident(NoResidentCarDTO noResidentCarDTO) {
        try {
            NoResidentCar noResidentCar = NoResidentCarMapper.mapToNoResidentCar(noResidentCarDTO);
            NoResidentCar noResidentSavedCar = noResidentCarRepository.save(noResidentCar);
            return NoResidentCarMapper.mapToNoResidentCarDto(noResidentSavedCar);
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    @Override
    public NoResidentCarDTO getNoResidentByPlate(String carPlate) {
        NoResidentCar residentCar = noResidentCarRepository.findByPlate(carPlate);
        if (residentCar == null){
            throw new ResourceNotFoundException("El auto con la placa: "+carPlate+" no se encontró");
        }
        return NoResidentCarMapper.mapToNoResidentCarDto(residentCar);
    }

    @Override
    public NoResidentCarDTO updateCarOut(String plate) {
        NoResidentCar noResidentCar = noResidentCarRepository.findByPlate(plate);
        noResidentCar.setOutRegister(LocalDateTime.now());
        Long minutes;
        Float amount;
        Duration duration = Duration.between(noResidentCar.getInRegister(), noResidentCar.getOutRegister());
        minutes = duration.toMinutes();
        amount = (float) (minutes*0.5);
        noResidentCar.setAmount((amount));
        NoResidentCar updatedCarObj;
        if (noResidentCar != null) {
            noResidentCar.setOutRegister(LocalDateTime.now());
            updatedCarObj = noResidentCarRepository.save(noResidentCar);
        } else {
            throw new ResourceNotFoundException("El auto con la placa: " + plate + " no se encontró");
        }
        return NoResidentCarMapper.mapToNoResidentCarDto(updatedCarObj);
    }

    @Override
    public NoResidentCarDTO updateCarIn(String carPLate) {
        return null;
    }
}
