package dev.edgarhernandez.parking.service;

import dev.edgarhernandez.parking.dto.ResidentCarDTO;
import dev.edgarhernandez.parking.entities.ResidentCar;
import dev.edgarhernandez.parking.utilities.ThreeColumnData;
import java.util.List;

public interface ResidentCarService {
    ResidentCarDTO createResident(ResidentCarDTO residentCarDTO);
    ResidentCarDTO getResidentByPlate(String plate);
    ResidentCarDTO updateCarOut(String carPLate);
    List<ResidentCar> getAllResidents();
    void updateResidentCarAcm();
    void generateFile(List<ThreeColumnData> data, String fileName);
    ResidentCarDTO updateAmountPerMonth(String plate);

}
