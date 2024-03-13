package dev.edgarhernandez.parking.repositories;

import dev.edgarhernandez.parking.entities.ResidentCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentCarRepository extends JpaRepository<ResidentCar, Long> {
    ResidentCar findByPlate(String plate);
}
