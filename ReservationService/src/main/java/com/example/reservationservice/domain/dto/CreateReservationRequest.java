package com.example.reservationservice.domain.dto;

import com.example.reservationservice.domain.Reservation;
import lombok.Getter;

@Getter
public class CreateReservationRequest {
    private Long ticketId;
    private String user_name;

    public Reservation toEntity(Long ticketId){
        return Reservation.builder()
                .user_name(this.user_name)
                .ticketId(ticketId)
                .build();
    }
    public CreateReservationRequest(Long ticket_id, String user_name){
        this.ticketId=ticket_id;
        this.user_name=user_name;
    }
}
