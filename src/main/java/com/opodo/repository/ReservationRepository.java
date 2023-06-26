package com.opodo.repository;

import com.opodo.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
