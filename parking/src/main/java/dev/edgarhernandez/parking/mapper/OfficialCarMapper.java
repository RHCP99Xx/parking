package dev.edgarhernandez.parking.mapper;

import dev.edgarhernandez.parking.dto.OfficialCarDTO;
import dev.edgarhernandez.parking.entities.OfficialCar;

public class OfficialCarMapper extends CarMapper{
    public static OfficialCarDTO mapToOfficialCarDto(OfficialCar officialCar){
        return new OfficialCarDTO(
                officialCar.getId(),
                officialCar.getPlate(),
                officialCar.getInRegister(),
                officialCar.getOutRegister(),
                officialCar.getStay()
        );
    }

    public static OfficialCar mapToOfficialCar(OfficialCarDTO officialCarDTO){
        return new OfficialCar(
                officialCarDTO.getId(),
                officialCarDTO.getPlate(),
                officialCarDTO.getInRegister(),
                officialCarDTO.getOutRegister(),
                officialCarDTO.getStay()
        );
    }
}
