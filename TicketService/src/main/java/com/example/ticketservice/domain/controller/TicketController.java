package com.example.ticketservice.domain.controller;

import com.example.ticketservice.domain.dto.CreateTicketRequest;
import com.example.ticketservice.domain.dto.CreateTicketResponse;
import com.example.ticketservice.domain.service.TicketService;
import com.example.ticketservice.global.kafka.KafkaProducer;
import com.example.ticketservice.global.kafka.StockCreateEvent;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket-service")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<CreateTicketResponse> create(@RequestBody CreateTicketRequest request){
        CreateTicketResponse response = ticketService.createTicket(request);
        StockCreateEvent event = response.toEvent();
        /* Kafka Messaging Queue에 전달 */
        kafkaProducer.send("ticket-stock-events", event);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
