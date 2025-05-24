package com.example.ticketstockservice.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ticketstockservice.domain.TicketStock;
import com.example.ticketstockservice.domain.repository.TicketStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketStockService {
    private final TicketStockRepository ticketStockRepository;

    @KafkaListener(topics = "ticket-stock-events")
    public void createStock(String kafkaMessage){
        log.info("Kafka Message: -> " + kafkaMessage);

        // 수신한 JSON 형식의 메시지를 Map으로 변환
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        }
        catch(JsonProcessingException ex){
            ex.printStackTrace();
        }
        Long ticketId = (Long)map.get("ticketId");
        TicketStock ticketStock = ticketStockRepository.findByTicketId(ticketId);
        if(ticketStock== null){
            ticketStock = TicketStock.builder().ticketId(ticketId).build();
            ticketStock = ticketStockRepository.save(ticketStock);
        }
    }

//    public Integer createTiketStock(Ticket ticket){
//        TicketStock ticketStock = ticketStockRepository.findByTicket(ticket).orElse(TicketStock.builder().ticket(ticket).build());
//        ticketStock = ticketStockRepository.save(ticketStock);
//        return ticketStock.getQuantity();
//    }
    public Integer addTicketStock(Long ticketId){
        TicketStock ticketStock = ticketStockRepository.findByTicketId(ticketId);
        return ticketStock.addTicketStock();
    }
    public Integer subicketStock(Long ticketId){
        TicketStock ticketStock = ticketStockRepository.findByTicketId(ticketId);
        return ticketStock.subTicketStock();
    }

}
