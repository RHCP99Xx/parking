package dev.edgarhernandez.parking.mapper;
import dev.edgarhernandez.parking.dto.StayDTO;
import dev.edgarhernandez.parking.entities.Stay;

public class StayMapper {
    public static StayDTO mapToStayDto(Stay stay){
        return new StayDTO(
                stay.getId(),
                stay.getStartDateTime(),
                stay.getEndDateTime(),
                stay.getOfficialCar()
        );
    }

    public static Stay mapToStay(StayDTO stayDTO){
        return new Stay(
                stayDTO.getId(),
                stayDTO.getStartDateTime(),
                stayDTO.getEndDateTime(),
                stayDTO.getOfficialCar()
        );
    }
}
