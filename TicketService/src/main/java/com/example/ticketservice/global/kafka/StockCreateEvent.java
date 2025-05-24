package com.example.ticketservice.global.kafka;

import lombok.Builder;

@Builder
public class StockCreateEvent {
    private Long ticketId;
    private String name;
}
