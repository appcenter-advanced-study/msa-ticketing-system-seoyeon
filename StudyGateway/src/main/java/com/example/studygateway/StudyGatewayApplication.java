package com.example.studygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class StudyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyGatewayApplication.class, args);
    }

}
