package com.example.reservationservice.domain.controller;

import com.example.reservationservice.domain.dto.CreateReservationResponse;
import com.example.reservationservice.domain.dto.SubStockRequest;
import com.example.reservationservice.domain.dto.SubStockResponse;
import com.example.reservationservice.domain.service.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.reservationservice.domain.dto.CreateReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CreateReservationResponse> create(@RequestBody CreateReservationRequest request) throws JsonProcessingException {
        // 티켓 스톡 감소 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9003/ticket-stock-service/sub-stock";
        SubStockRequest stockRequest = SubStockRequest.builder().ticketId(request.getTicketId()).build();
        ResponseEntity<SubStockResponse> result = restTemplate.postForEntity(url, stockRequest, SubStockResponse.class);
        SubStockResponse stock=result.getBody();
        //예약 생성
        CreateReservationResponse response = reservationService.createReservation(request, stock.getTicketStock());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
