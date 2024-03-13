package dev.edgarhernandez.parking.service;

import dev.edgarhernandez.parking.dto.CarDTO;
import dev.edgarhernandez.parking.dto.NoResidentCarDTO;

public interface NoResidentCarService {
    NoResidentCarDTO createNoResident(NoResidentCarDTO noResidentCarDTO);
    NoResidentCarDTO getNoResidentByPlate(String carPlate);

    NoResidentCarDTO updateCarOut(String carPLate);
    NoResidentCarDTO updateCarIn(String carPLate);
}
