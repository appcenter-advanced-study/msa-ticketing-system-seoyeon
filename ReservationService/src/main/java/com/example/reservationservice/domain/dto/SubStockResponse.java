package com.example.reservationservice.domain.dto;

import lombok.Getter;

@Getter
public class SubStockResponse {
    private Long ticketId;
    private Integer ticketStock;
}
