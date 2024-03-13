package dev.edgarhernandez.parking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NoResidentCarDTO extends CarDTO{
    private float amount;

    public NoResidentCarDTO(Long id, String plate, LocalDateTime inRegister, LocalDateTime outRegister, float amount) {
        super(id, plate, inRegister, outRegister);
        this.amount = amount;
    }
}
