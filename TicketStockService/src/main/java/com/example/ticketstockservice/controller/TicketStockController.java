package com.example.ticketstockservice.controller;

import com.example.ticketstockservice.dto.TicketStockResponse;
import com.example.ticketstockservice.service.TicketStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class TicketStockController {
    private final TicketStockService stockService;

    @PostMapping
    public ResponseEntity<Void> setStockQuantity(@RequestParam Long ticketId, @RequestParam Integer quantity) {
        stockService.setStockQuantity(ticketId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Integer> getStockQuantity(@RequestParam Long ticketStockId) {
        Integer result = stockService.findQuantityByTicketId(ticketStockId);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<TicketStockResponse> updateStockQuantity(@RequestParam Long ticketStockId, @RequestParam int quantity) {
        TicketStockResponse result = stockService.updateStockQuantity(ticketStockId, quantity);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{ticketId}/decrease")
    public ResponseEntity<Void> decreaseStock(@PathVariable Long ticketId) {
        stockService.decreaseStock(ticketId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{ticketId}/increase")
    public ResponseEntity<Void> increaseStock(@PathVariable Long ticketId) {
        stockService.increaseStock(ticketId);
        return ResponseEntity.ok().build();
    }


}