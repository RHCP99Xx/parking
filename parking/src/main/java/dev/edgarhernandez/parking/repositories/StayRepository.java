package dev.edgarhernandez.parking.repositories;
import dev.edgarhernandez.parking.entities.Stay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StayRepository extends JpaRepository<Stay, Long> {
    
}
