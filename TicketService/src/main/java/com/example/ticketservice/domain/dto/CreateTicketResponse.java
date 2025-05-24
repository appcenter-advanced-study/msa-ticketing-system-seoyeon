package com.example.ticketservice.domain.dto;

import com.example.ticketservice.global.kafka.StockCreateEvent;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTicketResponse {
    private Long id;
    private String name;
    private Integer ticket_stock;

    public StockCreateEvent toEvent(){
        return StockCreateEvent.builder()
                .ticketId(this.id)
                .name(this.name)
                .build();
    }
    public void updateStock(Integer stock){
        this.ticket_stock=stock;
    }
}
