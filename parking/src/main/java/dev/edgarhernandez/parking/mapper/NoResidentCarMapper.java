package dev.edgarhernandez.parking.mapper;

import dev.edgarhernandez.parking.dto.CarDTO;
import dev.edgarhernandez.parking.dto.NoResidentCarDTO;
import dev.edgarhernandez.parking.entities.Car;
import dev.edgarhernandez.parking.entities.NoResidentCar;

public class NoResidentCarMapper extends CarMapper{
    public static NoResidentCarDTO mapToNoResidentCarDto(NoResidentCar noResidentCar){
        return new NoResidentCarDTO(
                noResidentCar.getId(),
                noResidentCar.getPlate(),
                noResidentCar.getInRegister(),
                noResidentCar.getOutRegister(),
                noResidentCar.getAmount()
        );
    }

    public static NoResidentCar mapToNoResidentCar(NoResidentCarDTO noResidentCarDTO){
        return new NoResidentCar(
                noResidentCarDTO.getId(),
                noResidentCarDTO.getPlate(),
                noResidentCarDTO.getInRegister(),
                noResidentCarDTO.getOutRegister(),
                noResidentCarDTO.getAmount()
        );
    }
}

