package dev.edgarhernandez.parking.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "official_car")
@Entity
@PrimaryKeyJoinColumn(name="official_id")
public class OfficialCar extends Car{
    @Column(name = "stay")
    private LocalDateTime stay;

    public OfficialCar(Long id, String plate, LocalDateTime inRegister, LocalDateTime outRegister, LocalDateTime stay) {
        super(id, plate, inRegister, outRegister);
        this.stay = stay;
    }
}
