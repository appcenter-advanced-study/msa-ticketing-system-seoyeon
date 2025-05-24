package com.example.ticketstockservice.domain.repository;

import com.example.ticketstockservice.domain.TicketStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketStockRepository extends JpaRepository<TicketStock, Long> {
    //Optional<TicketStock> findByTicket(Ticket ticket);
    TicketStock findByTicketId(Long ticketId);
}
