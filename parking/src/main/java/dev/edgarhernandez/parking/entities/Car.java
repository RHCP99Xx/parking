package dev.edgarhernandez.parking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
@Inheritance(strategy = InheritanceType.JOINED)
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate")
    private String plate;
    @Column(name = "inRegister")
    private LocalDateTime inRegister;
    @Column(name = "outRegister")
    private LocalDateTime outRegister;
}
