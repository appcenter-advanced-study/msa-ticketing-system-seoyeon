package com.example.reservationservice.service;


import com.example.reservationservice.client.TicketStockClient;
import com.example.reservationservice.client.TicketClient;
import com.example.reservationservice.domain.Reservation;
import com.example.reservationservice.dto.ReservationResponse;
import com.example.reservationservice.dto.TicketResponse;
import com.example.reservationservice.event.ReservationCreatedEvent;
import com.example.reservationservice.event.ReservationEventPublisher;
import com.example.reservationservice.repository.ReservationOutBoxRepository;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.domain.ReservationOutBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final TicketStockClient stockClient;
    private final TicketClient ticketClient;
    private final ReservationOutBoxRepository reservationOutBoxRepository;
    private final ReservationEventPublisher reservationEventPublisher;
    private final ObjectMapper objectMapper;

    @Transactional
    public void createReservation(String username, Long ticketId) {
        // 재고 감소 요청
        stockClient.decreaseStock(ticketId);

        try {
            Thread.sleep(500); // 락 보유 시간을 인위적으로 증가
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 예약 생성
        Reservation reservation = new Reservation(username, ticketId);
        reservationRepository.save(reservation);

        // 예약 생성 이벤트 생성
        ReservationCreatedEvent reservationCreatedEvent = ReservationCreatedEvent.builder()
                .reservationId(reservation.getId())
                .username(username)
                .ticketId(ticketId)
                .build();

        // 직렬화
        String payload;

        try {
            payload = objectMapper.writeValueAsString(reservationCreatedEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Outbox 직렬화 실패", e);
        }

        ReservationOutBox reservationOutBox = ReservationOutBox.builder()
                .aggregateType("Reservation")
                .aggregateId(reservation.getId())
                .eventType("RESERVATION_CREATED")
                .payload(payload)
                .build();

        reservationOutBoxRepository.save(reservationOutBox);
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        // 예약 취소 이벤트로 해당 메서드 호출시 해당 건 삭제
        reservationRepository.delete(reservation);
    }

    @Transactional(readOnly = true)
    public ReservationResponse findById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        TicketResponse ticketResponse = ticketClient.getTicket(reservation.getTicketId());
        return new ReservationResponse(reservation.getId(), reservation.getUsername(), ticketResponse);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(reservation -> {
            TicketResponse ticketResponse = ticketClient.getTicket(reservation.getTicketId());
            return new ReservationResponse(reservation.getId(), reservation.getUsername(), ticketResponse);
        }).toList();
    }
    @Transactional
    public void deleteById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        stockClient.increaseStock(reservation.getTicketId());
        reservationRepository.delete(reservation);
    }
}
