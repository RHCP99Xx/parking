package dev.edgarhernandez.parking.mapper;

import dev.edgarhernandez.parking.dto.ResidentCarDTO;
import dev.edgarhernandez.parking.entities.ResidentCar;

public class ResidentCarMapper extends CarMapper{
    public static ResidentCarDTO mapToResidentCarDto(ResidentCar residentCar){
        return new ResidentCarDTO(
                residentCar.getId(),
                residentCar.getPlate(),
                residentCar.getInRegister(),
                residentCar.getOutRegister(),
                residentCar.getAcumHours(),
                residentCar.getAmountPerMonth()
        );
    }

    public static ResidentCar mapToResidentCar(ResidentCarDTO residentCarDTO){
        return new ResidentCar(
                residentCarDTO.getId(),
                residentCarDTO.getPlate(),
                residentCarDTO.getInRegister(),
                residentCarDTO.getOutRegister(),
                residentCarDTO.getAcumHours(),
                residentCarDTO.getAmountPerMonth()
        );
    }
}
