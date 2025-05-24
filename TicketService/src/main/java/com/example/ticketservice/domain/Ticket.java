package com.example.ticketservice.domain;

import com.example.ticketservice.domain.dto.CreateTicketResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;

    private String name;
//
//    @OneToMany(mappedBy = "ticket")
//    private List<Reservation> reservations;
//
//    @OneToOne(mappedBy = "ticket")
//    private TicketStock ticketStock;

    @Builder
    private Ticket(String name){
        this.name=name;
    }

    public CreateTicketResponse toDto(){
        return CreateTicketResponse.builder()
                .id(this.ticket_id)
                .name(this.name)
                .ticket_stock(10)
                .build();
    }
}
