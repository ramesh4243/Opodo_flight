package com.opodo.service.impl;

import com.opodo.payload.ReservationDto;

public interface ReservationService {
    ReservationDto createReservation(ReservationDto reservationDto);

    ReservationDto getReservationById(Long id);

    String deleteReservation(Long id);
}
