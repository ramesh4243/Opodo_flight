package com.opodo.service.impl;

import com.opodo.entities.Flight;
import com.opodo.payload.FlightDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface FlightService {
    FlightDto createFlight(FlightDto flightDto);
    FlightDto updateFlight(Long flightId,FlightDto flightDto);
    FlightDto getFlightById(Long flightId);
    boolean deleteFlightById(Long flightId);
    Page<FlightDto>getFlights(Pageable pageable);
    List<Flight> searchFlights(String departureCity, String arrivalCity, Date dateOfDeparture, String flightType);
}
