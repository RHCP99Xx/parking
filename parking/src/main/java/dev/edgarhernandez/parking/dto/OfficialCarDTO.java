package dev.edgarhernandez.parking.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OfficialCarDTO extends CarDTO{
    private LocalDateTime stay;

    public OfficialCarDTO(Long id, String plate, LocalDateTime inRegister, LocalDateTime outRegister, LocalDateTime stay) {
        super(id, plate, inRegister, outRegister);
        this.stay = stay;
    }
}
