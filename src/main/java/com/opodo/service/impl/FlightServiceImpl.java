package com.opodo.service.impl;

import com.opodo.entities.Flight;
import com.opodo.exception.ResourceNotFoundException;
import com.opodo.payload.FlightDto;
import com.opodo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository=flightRepository;
    }


    @Override
    public FlightDto createFlight(FlightDto flightDto) {
        Flight flight =new Flight();
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setOperatingAirline(flightDto.getOperatingAirline());
        flight.setDepartureCity(flightDto.getDepartureCity());
        flight.setArrivalCity(flightDto.getArrivalCity());
        flight.setDateOfDeparture(flightDto.getDateOfDeparture());
        flight.setEstimatedDepartureTime(flightDto.getEstimatedDepartureTime());
        flight.setFlightType(flightDto.getFlightType());
        Flight savedFlight = flightRepository.save(flight);

        FlightDto dto = new FlightDto();
        dto.setId(savedFlight.getId());
        dto.setFlightNumber(savedFlight.getFlightNumber());
        dto.setOperatingAirline(savedFlight.getOperatingAirline());
        dto.setDepartureCity(savedFlight.getDepartureCity());
        dto.setArrivalCity(savedFlight.getArrivalCity());
        dto.setDateOfDeparture(savedFlight.getDateOfDeparture());
        dto.setEstimatedDepartureTime(savedFlight.getEstimatedDepartureTime());
        dto.setFlightType(savedFlight.getFlightType());
        return dto;
    }

    @Override
    public FlightDto updateFlight(Long flightId, FlightDto flightDto) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new
                ResourceNotFoundException("Flight not Found"));
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setOperatingAirline(flightDto.getOperatingAirline());
        flight.setDepartureCity(flightDto.getDepartureCity());
        flight.setArrivalCity(flightDto.getArrivalCity());
        flight.setDateOfDeparture(flightDto.getDateOfDeparture());
        flight.setEstimatedDepartureTime(flightDto.getEstimatedDepartureTime());
        flight.setFlightType(flightDto.getFlightType());
        Flight saved = flightRepository.save(flight);
        return flightToDto(saved);

    }

    @Override
    public FlightDto getFlightById(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new
                ResourceNotFoundException("Flight not found."));
        return flightToDto(flight);
    }

    @Override
    public boolean deleteFlightById(Long flightId) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if (optionalFlight.isPresent()) {
            flightRepository.delete(optionalFlight.get());
            return true;
        }
        return false;
    }

    @Override
    public Page<FlightDto> getFlights(Pageable pageable) {
        Page<Flight> flightPage = flightRepository.findAll(pageable);
        List<FlightDto> flightDtos = flightPage.stream().map(this::flightToDto).collect(Collectors.toList());
        return new PageImpl<>(flightDtos,pageable,flightPage.getTotalElements());
    }
    @Override
    public List<Flight> searchFlights(String departureCity, String arrivalCity, Date dateOfDeparture, String flightType) {
        return flightRepository.searchFlights(departureCity, arrivalCity, dateOfDeparture,flightType);
    }


    FlightDto flightToDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setOperatingAirline(flight.getOperatingAirline());
        flightDto.setDepartureCity(flight.getDepartureCity());
        flightDto.setArrivalCity(flight.getArrivalCity());
        flightDto.setDateOfDeparture(flight.getDateOfDeparture());
        flightDto.setEstimatedDepartureTime(flight.getEstimatedDepartureTime());
        flightDto.setFlightType(flight.getFlightType());
        return flightDto;

    }
}
