package com.opodo.controller;

import com.opodo.payload.PassengerDto;
import com.opodo.service.impl.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/passenger")
@Validated
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService){
        this.passengerService=passengerService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createPassenger(@Valid @RequestBody PassengerDto passengerDto, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PassengerDto createPassenger = passengerService.createPassenger(passengerDto);
        return new ResponseEntity<>(createPassenger, HttpStatus.CREATED);

    }
    @PutMapping("/{passengerId}")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable Long passengerId,@RequestBody PassengerDto passengerDto){
        PassengerDto updatePassenger = passengerService.updatePassenger(passengerId, passengerDto);
        return new ResponseEntity<>(updatePassenger,HttpStatus.OK);
    }
    @DeleteMapping("/{passengerId}")
    public ResponseEntity<String> deletePassengerById(@PathVariable Long passengerId){
        passengerService.deletePassengerById(passengerId);
        return new ResponseEntity<>("Passenger is deleted.",HttpStatus.OK);
    }
    @GetMapping("/{passengerId}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable Long passengerId){
       return new ResponseEntity<>(passengerService.getPassengerById(passengerId),HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<Page<PassengerDto>>getPassengers(@PageableDefault (size = 10, sort = "id")Pageable pageable){
        Page<PassengerDto> passengers = passengerService.getPassengers(pageable);
        return new ResponseEntity<>(passengers,HttpStatus.OK);
    }
}
