package dev.edgarhernandez.parking.service.implementation;

import dev.edgarhernandez.parking.dto.OfficialCarDTO;
import dev.edgarhernandez.parking.entities.OfficialCar;
import dev.edgarhernandez.parking.exceptions.ResourceNotFoundException;
import dev.edgarhernandez.parking.mapper.OfficialCarMapper;
import dev.edgarhernandez.parking.repositories.OfficialCarRepository;
import dev.edgarhernandez.parking.service.OfficialCarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class OfficialCarServiceImpl implements OfficialCarService {
    private OfficialCarRepository officialCarRepository;
    @Override
    public OfficialCarDTO createOfficial(OfficialCarDTO officialCarDTO) {
        try {
            OfficialCar officialCar = OfficialCarMapper.mapToOfficialCar(officialCarDTO);
            OfficialCar officialSavedCar = officialCarRepository.save(officialCar);
            return OfficialCarMapper.mapToOfficialCarDto(officialSavedCar);
        }catch (Exception e){
            e.getMessage();
            return null;
        }

    }

    @Override
    public OfficialCarDTO getOfficialByPlate(String plate) {
        OfficialCar officialCar = officialCarRepository.findByPlate(plate);
        if (officialCar == null){
            throw new ResourceNotFoundException("El auto con la placa: "+plate+" no se encontró");
        }
        return OfficialCarMapper.mapToOfficialCarDto(officialCar);
    }

    @Override
    public OfficialCarDTO updateCarOut(String plate) {
        OfficialCar officialCar = officialCarRepository.findByPlate(plate);
        OfficialCar updatedCarObj;
        if (officialCar != null) {
            officialCar.setOutRegister(LocalDateTime.now());
            updatedCarObj = officialCarRepository.save(officialCar);
        } else {
            throw new ResourceNotFoundException("El auto con la placa: " + plate + " no se encontró");
        }
        return OfficialCarMapper.mapToOfficialCarDto(updatedCarObj);
    }
}
