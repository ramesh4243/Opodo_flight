package com.opodo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "operating_airline")
    private String operatingAirline;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "date_of_departure")
    private Date dateOfDeparture;

    @Column(name = "estimated_departure_time")
    private Timestamp estimatedDepartureTime;

    @Column(name = "flight_type")
    private String flightType;

    @OneToOne(mappedBy = "flight")
    private Reservation reservation;


    }

