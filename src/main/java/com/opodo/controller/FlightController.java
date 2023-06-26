package com.opodo.controller;

import com.opodo.entities.Flight;
import com.opodo.payload.FlightDto;
import com.opodo.service.impl.FlightService;
import com.opodo.service.impl.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private final FlightService flightService;



    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    //pagination and shorting
    //http://localhost:8080/api/flight?page=0&size=10&sort=id,asc
    @GetMapping
    public ResponseEntity<Page<FlightDto>> getFlights(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<FlightDto> flights = flightService.getFlights(pageable);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long flightId) {
        return new ResponseEntity<>(flightService.getFlightById(flightId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        FlightDto createdFlight = flightService.createFlight(flightDto);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{flightId}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long flightId, @RequestBody FlightDto flightDto) {
        FlightDto updatedFlight = flightService.updateFlight(flightId, flightDto);
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);

    }

@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{flightId}")
public ResponseEntity<String> deleteFlightById(@PathVariable Long flightId) {
    if (flightService.deleteFlightById(flightId)) {
        return new ResponseEntity<>("Flight is deleted", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
    }
}



    // http://localhost:8080/api/flight/search?departureCity={departureCity}&arrivalCity={arrivalCity}&dateOfDeparture={dateOfDeparture}&flightType={flightType}
    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam("departureCity") String departureCity,
            @RequestParam("arrivalCity") String arrivalCity,
            @RequestParam("dateOfDeparture") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfDeparture,
            @RequestParam("flightType") String flightType,
            @RequestParam("numAdults") int numAdults

    ) {
        List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity, dateOfDeparture, flightType);
        if (flights.isEmpty()) {
            String message = "Flight not available for the given criteria.";
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(Collections.singletonMap("message", message));
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(flights);
    }

}