package com.example.ticketstockservice.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubStockResponse {
    private Long ticketId;
    private Integer ticketStock;
}
