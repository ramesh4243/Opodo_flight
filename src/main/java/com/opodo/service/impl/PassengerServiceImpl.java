package com.opodo.service.impl;

import com.opodo.entities.Passenger;
import com.opodo.exception.ResourceNotFoundException;
import com.opodo.payload.PassengerDto;
import com.opodo.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService{
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository){
        this.passengerRepository=passengerRepository;
    }

    @Override
    public PassengerDto createPassenger(PassengerDto passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setSurName(passengerDto.getSurName());
        passenger.setGender(passengerDto.getGender());
        passenger.setDateOfBirth(passengerDto.getDateOfBirth());
        passenger.setPhone(passengerDto.getPhone());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setConfirmEmail(passengerDto.getConfirmEmail());
        passenger.setCountryCode(passengerDto.getCountryCode());
        passenger.setAddress(passengerDto.getAddress());
        passenger.setPostcode(passengerDto.getPostcode());
        passenger.setCity(passengerDto.getCity());
        passenger.setCountry(passengerDto.getCountry());
        Passenger saved = passengerRepository.save(passenger);

        PassengerDto Dto = new PassengerDto();
        Dto.setId(saved.getId());
        Dto.setFirstName(saved.getFirstName());
        Dto.setSurName(saved.getSurName());
        Dto.setGender(saved.getGender());
        Dto.setDateOfBirth(saved.getDateOfBirth());
        Dto.setPhone(saved.getPhone());
        Dto.setEmail(saved.getEmail());
        Dto.setConfirmEmail(saved.getConfirmEmail());
        Dto.setCountryCode(saved.getCountryCode());
        Dto.setAddress(saved.getAddress());
        Dto.setPostcode(saved.getPostcode());
        Dto.setCity(saved.getCity());
        Dto.setCountry(saved.getCountry());
        return Dto;
    }

    @Override
    public PassengerDto updatePassenger(Long passengerId, PassengerDto passengerDto) {
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new
                ResourceNotFoundException("Passenger not found."));
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setSurName(passengerDto.getSurName());
        passenger.setGender(passengerDto.getGender());
        passenger.setDateOfBirth(passengerDto.getDateOfBirth());
        passenger.setPhone(passengerDto.getPhone());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setConfirmEmail(passengerDto.getConfirmEmail());
        passenger.setCountryCode(passengerDto.getCountryCode());
        passenger.setAddress(passengerDto.getAddress());
        passenger.setPostcode(passengerDto.getPostcode());
        passenger.setCity(passengerDto.getCity());
        passenger.setCountry(passengerDto.getCountry());
        Passenger passengersaved = passengerRepository.save(passenger);
        return passengerToDto(passengersaved);
    }

    @Override
    public PassengerDto deletePassengerById(Long passengerId) {
        Passenger passengersaved = passengerRepository.findById(passengerId).orElseThrow(() -> new
                ResourceNotFoundException("Passenger not found."));
        return passengerToDto(passengersaved);
    }

    @Override
    public PassengerDto getPassengerById(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new
                ResourceNotFoundException("Passenger not found"));
        return passengerToDto(passenger);
    }

    @Override
    public Page<PassengerDto> getPassengers(Pageable pageable) {
        Page<Passenger> passengerPage = passengerRepository.findAll(pageable);
        List<PassengerDto> collect = passengerPage.stream().map(this::passengerToDto).collect(Collectors.toList());
        return new PageImpl<>(collect,pageable,passengerPage.getTotalElements());
    }



    PassengerDto passengerToDto(Passenger passengersaved) {
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setId(passengersaved.getId());
        passengerDto.setFirstName(passengersaved.getFirstName());
        passengerDto.setSurName(passengersaved.getSurName());
        passengerDto.setGender(passengersaved.getGender());
        passengerDto.setDateOfBirth(passengersaved.getDateOfBirth());
        passengerDto.setPhone(passengersaved.getPhone());
        passengerDto.setEmail(passengersaved.getEmail());
        passengerDto.setConfirmEmail(passengerDto.getConfirmEmail());
        passengerDto.setCountryCode(passengersaved.getCountryCode());
        passengerDto.setPostcode(passengersaved.getPostcode());
        passengerDto.setCity(passengersaved.getCity());
        passengerDto.setCountry(passengersaved.getCountry());
        return passengerDto;
    }
}
