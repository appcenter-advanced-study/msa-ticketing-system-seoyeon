package com.example.ticketstockservice.dto;



public record TicketStockResponse(
        Long id,
        Integer quantity,
        TicketResponse ticket
) {
}