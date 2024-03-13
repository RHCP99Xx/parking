package dev.edgarhernandez.parking.service;

import dev.edgarhernandez.parking.dto.OfficialCarDTO;

public interface OfficialCarService {
    OfficialCarDTO createOfficial(OfficialCarDTO officialCarDTO);
    OfficialCarDTO getOfficialByPlate(String plate);
    OfficialCarDTO updateCarOut(String carPLate);
    OfficialCarDTO updateCarIn(String carPLate);
}
