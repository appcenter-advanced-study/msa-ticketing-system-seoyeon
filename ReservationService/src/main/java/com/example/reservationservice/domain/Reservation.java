package com.example.reservationservice.domain;

import com.example.reservationservice.domain.dto.CreateReservationResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ticket_id", nullable = false)
    private Long ticketId;

    @Builder
    private Reservation(String user_name, Long ticketId){
        this.user_name=user_name; this.ticketId=ticketId;
    }

    public CreateReservationResponse toDto(Integer ticketStock){
        return CreateReservationResponse.builder()
                .reservation_id(this.id)
                .ticket_id(this.ticketId)
                .user_name(this.user_name)
                .ticket_stock(ticketStock)
                .build();
    }
}
