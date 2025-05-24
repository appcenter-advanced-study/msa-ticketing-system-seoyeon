package com.example.ticketservice.domain.service;

import com.example.ticketservice.global.exception.NotFoundTicketException;
import com.example.ticketservice.domain.Ticket;
import com.example.ticketservice.domain.dto.CreateTicketRequest;
import com.example.ticketservice.domain.dto.CreateTicketResponse;
import com.example.ticketservice.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public CreateTicketResponse createTicket(CreateTicketRequest request){
        Ticket ticket = request.toEntity();
        ticket = ticketRepository.save(ticket);
        //Integer ticketStock = ticketStockService.createTiketStock(ticket);
        return ticket.toDto();
    }
    public Ticket getTicket(Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(()->new NotFoundTicketException("ticket이 없습니다."));
        return ticket;
    }
}
