package com.example.ticketstockservice.domain.controller;

import com.example.ticketstockservice.domain.dto.SubStockRequest;
import com.example.ticketstockservice.domain.dto.SubStockResponse;
import com.example.ticketstockservice.domain.service.TicketStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket-stock-service")
@RequiredArgsConstructor
public class TicketStockController {
    private final TicketStockService ticketStockService;
    @PostMapping("/sub-stock")
    public ResponseEntity<SubStockResponse> subTicketStock(@RequestBody SubStockRequest request) {
        Integer stock = ticketStockService.subicketStock(request.getTicketId());

        SubStockResponse response = SubStockResponse.builder().ticketStock(stock).ticketId(request.getTicketId()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
