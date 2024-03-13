package dev.edgarhernandez.parking.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "no_resident_car")
@Entity
@PrimaryKeyJoinColumn(name="no_resident_id")
public class NoResidentCar extends Car{
    @Column(name = "amount")
    private float amount;

    public NoResidentCar(Long id, String plate, LocalDateTime inRegister, LocalDateTime outRegister, float amount) {
        super(id, plate, inRegister, outRegister);
        this.amount = amount;
    }
}
