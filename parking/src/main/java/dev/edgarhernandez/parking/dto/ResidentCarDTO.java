package dev.edgarhernandez.parking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResidentCarDTO extends CarDTO{
    private String acumHours;
    private Float amountPerMonth;

    public ResidentCarDTO(Long id, String plate, LocalDateTime inRegister, LocalDateTime outRegister, String acumHours, Float amountPerMonth) {
        super(id, plate, inRegister, outRegister);
        this.acumHours = acumHours;
        this.amountPerMonth=amountPerMonth;
    }
}
