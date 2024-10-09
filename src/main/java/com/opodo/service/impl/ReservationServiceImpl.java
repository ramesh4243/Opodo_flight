package com.opodo.service.impl;

import com.opodo.entities.Flight;
import com.opodo.entities.Passenger;
import com.opodo.entities.Reservation;
import com.opodo.exception.ResourceNotFoundException;
import com.opodo.payload.ReservationDto;
import com.opodo.repository.FlightRepository;
import com.opodo.repository.PassengerRepository;
import com.opodo.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  PassengerRepository passengerRepository,
                                  FlightRepository flightRepository,
                                  ModelMapper modelMapper){
        this.reservationRepository = reservationRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {

        Flight flight = flightRepository.findById(reservationDto.getFlight().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with ID: " + reservationDto.getFlight().getId()));

        Passenger passenger = passengerRepository.findById(reservationDto.getPassenger().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with ID: " + reservationDto.getPassenger().getId()));

        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);

        Reservation savedReservation = reservationRepository.save(reservation);

        return modelMapper.map(savedReservation, ReservationDto.class);

    }
    @Override
    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));

        return modelMapper.map(reservation, ReservationDto.class);
    }

    @Override
    public String deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));

        reservationRepository.delete(reservation);

        return "Reservation with ID: " + id + " has been deleted.";
    }

}
