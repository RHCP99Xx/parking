package dev.edgarhernandez.parking.service;

import dev.edgarhernandez.parking.dto.StayDTO;
import dev.edgarhernandez.parking.entities.Stay;

public interface StaySevice {
    public StayDTO createStay(StayDTO stayDTO);
    public void deleteAll();
}
