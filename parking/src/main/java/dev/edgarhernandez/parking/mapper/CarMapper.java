package dev.edgarhernandez.parking.mapper;

import dev.edgarhernandez.parking.dto.CarDTO;
import dev.edgarhernandez.parking.entities.Car;

public class CarMapper {

    public static CarDTO mapToCarDto(Car car){
        return new CarDTO(
                car.getId(),
                car.getPlate(),
                car.getInRegister(),
                car.getOutRegister()
        );
    }

    public static Car mapToCar(CarDTO carDto){
        return new Car(
                carDto.getId(),
                carDto.getPlate(),
                carDto.getInRegister(),
                carDto.getOutRegister()
        );
    }
}
