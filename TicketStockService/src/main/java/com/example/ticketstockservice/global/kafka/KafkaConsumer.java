package com.example.ticketstockservice.global.kafka;

import com.example.ticketstockservice.domain.TicketStock;
import com.example.ticketstockservice.domain.repository.TicketStockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {
    private final TicketStockRepository ticketStockRepository;

//    @KafkaListener(topics = "ticket-stock-events")
//    public void updateStock(String kafkaMessage){
//        log.info("Kafka Message: -> " + kafkaMessage);
//
//        // 수신한 JSON 형식의 메시지를 Map으로 변환
//        Map<Object, Object> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//        try{
//            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
//        }
//        catch(JsonProcessingException ex){
//            ex.printStackTrace();
//        }
//        Long ticketId = (Long)map.get("ticketId");
//        TicketStock entity = ticketStockRepository.findByTicketId(ticketId);
//        if(entity != null){
//            entity.subTicketStock();
//            ticketStockRepository.save(entity);
//        }
//    }

}