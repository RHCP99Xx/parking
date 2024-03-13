package dev.edgarhernandez.parking.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "resident_car")
@Entity
@PrimaryKeyJoinColumn(name="resident_id")
public class ResidentCar extends Car{
    @Column(name = "acumHours")
    private String acumHours;
    @Column(name = "amountPerMonth")
    private Float amountPerMonth;

    public ResidentCar(Long id, String plate, LocalDateTime inRegister, LocalDateTime outRegister, String acumHours, Float amountPerMonth) {
        super(id, plate, inRegister, outRegister);
        this.acumHours = acumHours;
        this.amountPerMonth=amountPerMonth;
    }
}
