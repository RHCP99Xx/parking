package dev.edgarhernandez.parking.service.implementation;

import dev.edgarhernandez.parking.dto.StayDTO;
import dev.edgarhernandez.parking.entities.Stay;
import dev.edgarhernandez.parking.mapper.StayMapper;
import dev.edgarhernandez.parking.repositories.StayRepository;
import dev.edgarhernandez.parking.service.StaySevice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StayServiceImpl implements StaySevice {
    private StayRepository stayRepository;
    @Override
    public StayDTO createStay(StayDTO stayDTO) {
        Stay stay = StayMapper.mapToStay(stayDTO);
        Stay staySaved = stayRepository.save(stay);
        return StayMapper.mapToStayDto(staySaved);
    }
    @Override
    public void deleteAll(){
        stayRepository.deleteAll();
    }
}
