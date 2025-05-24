package com.example.ticketservice.domain.dto;

import com.example.ticketservice.domain.Ticket;
import lombok.Getter;

@Getter
public class CreateTicketRequest {
    private String name;

    public Ticket toEntity(){
        return Ticket.builder()
                .name(this.name)
                .build();
    }

    public CreateTicketRequest(String name){
        this.name = name;
    }
}
