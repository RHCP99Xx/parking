package dev.edgarhernandez.parking.service;

import dev.edgarhernandez.parking.dto.CarDTO;

import java.time.LocalDateTime;

public interface CarService {

    CarDTO createCar(CarDTO carDto);

    CarDTO getCarByPlate(String carPlate);

    CarDTO updateCarOut(String carPLate);
    CarDTO updateCarIn(String carPLate);
}
