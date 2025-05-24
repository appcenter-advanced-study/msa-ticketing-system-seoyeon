package com.example.reservationservice.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReservationResponse {
    private Long reservation_id;
    private Long ticket_id;
    private String user_name;
    private Integer ticket_stock;
}
