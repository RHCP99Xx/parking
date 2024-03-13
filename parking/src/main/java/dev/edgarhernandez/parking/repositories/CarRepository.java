package dev.edgarhernandez.parking.repositories;

import dev.edgarhernandez.parking.dto.CarDTO;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.edgarhernandez.parking.entities.Car;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByPlate(String plate);
}
