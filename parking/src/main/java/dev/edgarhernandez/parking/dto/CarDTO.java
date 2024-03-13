package dev.edgarhernandez.parking.dto;

import dev.edgarhernandez.parking.utilities.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CarDTO {
    private Long id;
    private String plate;
    private LocalDateTime inRegister;
    private LocalDateTime outRegister;
}
