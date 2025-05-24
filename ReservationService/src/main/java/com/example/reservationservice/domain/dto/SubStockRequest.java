package com.example.reservationservice.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubStockRequest {
    private Long ticketId;
}
