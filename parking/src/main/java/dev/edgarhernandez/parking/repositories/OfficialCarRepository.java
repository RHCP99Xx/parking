package dev.edgarhernandez.parking.repositories;

import dev.edgarhernandez.parking.entities.OfficialCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficialCarRepository extends JpaRepository<OfficialCar, Long> {
    OfficialCar findByPlate(String plate);
}
