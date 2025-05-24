package com.example.reservationservice.domain.service;

import com.example.reservationservice.domain.Reservation;
import com.example.reservationservice.domain.dto.CreateReservationRequest;
import com.example.reservationservice.domain.dto.CreateReservationResponse;
import com.example.reservationservice.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public CreateReservationResponse createReservation(CreateReservationRequest request, Integer ticketStock){
        Reservation reservation = reservationRepository.findByTicketId(request.getTicketId());
        if (reservation==null){
            reservation = request.toEntity(request.getTicketId());
            reservation = reservationRepository.save(reservation);
        }
        return reservation.toDto(ticketStock);
    }
}
