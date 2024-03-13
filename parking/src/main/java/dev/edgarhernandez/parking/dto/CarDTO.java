package dev.edgarhernandez.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
