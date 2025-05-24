package com.example.ticketstockservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TicketStockServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketStockServiceApplication.class, args);
    }

}
