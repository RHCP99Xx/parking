package dev.edgarhernandez.parking.service.implementation;

import dev.edgarhernandez.parking.dto.CarDTO;
import dev.edgarhernandez.parking.entities.Car;
import dev.edgarhernandez.parking.exceptions.ResourceNotFoundException;
import dev.edgarhernandez.parking.mapper.CarMapper;
import dev.edgarhernandez.parking.repositories.CarRepository;
import dev.edgarhernandez.parking.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    /*
    * This method allows to create a new car entity in the database
    * */
    @Override
    public CarDTO createCar(CarDTO carDto) {
        Car car = CarMapper.mapToCar(carDto);
        Car savedCar = carRepository.save(car);
        return CarMapper.mapToCarDto(savedCar);
    }

    /*
     * This method allows to get a car by using the plate as an identifier
     * */
    @Override
    public CarDTO getCarByPlate(String carPlate) {
        Car car = carRepository.findByPlate(carPlate);
        if (car == null){
            throw new ResourceNotFoundException("El auto con la placa: "+carPlate+" no se encontró");
        }
        return CarMapper.mapToCarDto(car);
    }

    @Override
    public CarDTO updateCarOut(String carPlate) {
        Car car = carRepository.findByPlate(carPlate);
        Car updatedCarObj;
        if (car != null) {
            car.setOutRegister(LocalDateTime.now());
            updatedCarObj = carRepository.save(car);
        } else {
            throw new ResourceNotFoundException("El auto con la placa: " + carPlate + " no se encontró");
        }
        return CarMapper.mapToCarDto(updatedCarObj);
    }

    @Override
    public CarDTO updateCarIn(String carPlate) {
        Car car = carRepository.findByPlate(carPlate);
        Car updatedCarObj;
        if (car != null) {
            car.setInRegister(LocalDateTime.now());
            updatedCarObj = carRepository.save(car);
        } else {
            throw new ResourceNotFoundException("El auto con la placa: " + carPlate + " no se encontró");
        }
        return CarMapper.mapToCarDto(updatedCarObj);
    }
}
