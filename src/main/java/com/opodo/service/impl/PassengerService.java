package com.opodo.service.impl;

import com.opodo.payload.PassengerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PassengerService {
    PassengerDto createPassenger(PassengerDto passengerDto);
    PassengerDto updatePassenger(Long passengerId, PassengerDto passengerDto);
    PassengerDto deletePassengerById(Long passengerId);
    PassengerDto getPassengerById(Long passengerId);
    Page<PassengerDto>getPassengers(Pageable pageable);




}
