package com.example.reservationservice.dto;

public record ReservationResponse(
        Long id,
        String username,
        TicketResponse ticket
) {
}