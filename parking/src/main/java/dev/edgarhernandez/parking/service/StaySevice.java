package dev.edgarhernandez.parking.service;

import dev.edgarhernandez.parking.dto.StayDTO;

public interface StaySevice {
    public StayDTO createStay(StayDTO stayDTO);
    public void deleteAll();
}
