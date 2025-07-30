package com.example.ticketservice.service;

import com.example.ticketservice.domain.Ticket;
import com.example.ticketservice.dto.TicketResponse;
import com.example.ticketservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public void createTicket(String name) {
        Ticket ticket = new Ticket(name);
        ticketRepository.save(ticket);
    }

    public TicketResponse findById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        return new TicketResponse(ticket.getId(), ticket.getName());
    }

    public List<TicketResponse> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticket -> new TicketResponse(ticket.getId(), ticket.getName())).toList();
    }

    public TicketResponse updateTicket(Long id, String name) {
        Ticket foundTicket = ticketRepository.findById(id).orElseThrow();
        foundTicket.updateName(name);
        ticketRepository.save(foundTicket);
        return new TicketResponse(foundTicket.getId(), foundTicket.getName());
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}