package dev.edgarhernandez.parking.repositories;

import dev.edgarhernandez.parking.entities.Car;
import dev.edgarhernandez.parking.entities.NoResidentCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoResidentCarRepository extends JpaRepository<NoResidentCar, Long> {
    NoResidentCar findByPlate(String plate);
}
