package com.example.ticketstockservice.domain;

import com.example.ticketstockservice.global.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ticket_id", nullable = false)
    private Long ticketId;

    @Builder
    public TicketStock(Long ticketId){
        this.quantity=10;
        this.ticketId=ticketId;
    }

    public Integer addTicketStock(){
        this.quantity+=1;
        return this.quantity;
    }

    public Integer subTicketStock(){
        if (this.quantity==0){
            throw new OutOfStockException("티켓의 현재 재고가 0개입니다.");
        }
        this.quantity-=1;
        return this.quantity;
    }
}
