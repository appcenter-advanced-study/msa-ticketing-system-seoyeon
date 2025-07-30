package com.example.reservationservice.repository;

import com.example.reservationservice.domain.ReservationOutBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationOutBoxRepository extends JpaRepository<ReservationOutBox, Long> {
}