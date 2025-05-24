package com.study2025.studyeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StudyEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyEurekaServerApplication.class, args);
    }

}
