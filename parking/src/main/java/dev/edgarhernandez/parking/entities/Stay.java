package dev.edgarhernandez.parking.entities;

import dev.edgarhernandez.parking.dto.OfficialCarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "stay")
@Entity
public class Stay {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_datetime")
    private LocalDateTime startDateTime;
    @Column(name = "end_datetime")
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "official_id")
    private OfficialCar officialCar;
}
