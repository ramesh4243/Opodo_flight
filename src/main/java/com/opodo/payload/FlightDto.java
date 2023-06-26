package com.opodo.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
public class FlightDto {
    private Long id;
    private String flightNumber;
    private String operatingAirline;
    private String departureCity;
    private String arrivalCity;
    private Date dateOfDeparture;
    private Timestamp estimatedDepartureTime;
    private String flightType;
}

