package com.example.ticketstockservice.service;

import com.example.ticketstockservice.client.TicketClient;
import com.example.ticketstockservice.domain.TicketStock;
import com.example.ticketstockservice.dto.TicketStockResponse;
import com.example.ticketstockservice.dto.TicketResponse;
import com.example.ticketstockservice.repository.TicketStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketStockService {
    private final TicketStockRepository stockRepository;
    private final TicketClient ticketClient;

    public Integer findQuantityByTicketId(Long ticketStockId) {
        TicketStock stock = stockRepository.findById(ticketStockId).orElseThrow();
        return stock.getQuantity();
    }

    public void setStockQuantity(Long ticketId, Integer quantity) {
        TicketStock stock = new TicketStock(quantity, ticketId);
        stockRepository.save(stock);
    }

    public TicketStockResponse updateStockQuantity(Long ticketId, Integer quantity) {
        TicketStock stock = stockRepository.findById(ticketId).orElseThrow();
        stock.updateQuantity(quantity);
        TicketStock savedTicketStock = stockRepository.save(stock);
        TicketResponse ticketResponse = ticketClient.getTicket(ticketId);
        return new TicketStockResponse(savedTicketStock.getId(), savedTicketStock.getQuantity(), ticketResponse);
    }

    @Transactional
    public void decreaseStock(Long ticketId) {
        TicketStock stock = stockRepository.findByTicketId(ticketId);
        stock.decreaseQuantity();
        stockRepository.save(stock);
    }

    @Transactional
    public void increaseStock(Long ticketId) {
        TicketStock stock = stockRepository.findByTicketId(ticketId);
        stock.increaseQuantity();
        stockRepository.save(stock);
    }
}