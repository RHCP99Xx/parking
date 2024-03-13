package dev.edgarhernandez.parking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.edgarhernandez.parking.entities.Car;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByPlate(String plate);
}
