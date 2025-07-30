package com.example.ticketstockservice.repository;


import com.example.ticketstockservice.domain.TicketStock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface TicketStockRepository extends JpaRepository<TicketStock, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    TicketStock findByTicketId(Long ticketId);
}