package com.opodo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    private boolean checkIn;
    private int numberOfBags;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id",referencedColumnName = "id")
    private Passenger passenger;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id",referencedColumnName = "id")
    private Flight flight;

}
