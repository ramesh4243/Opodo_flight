package com.opodo.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationDto {
    private Long id;
    private boolean checkIn;
    private int numberOfBags;
    private PassengerDto passenger;
    private FlightDto flight;

}

