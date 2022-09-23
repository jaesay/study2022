package com.example.springsingletonconcurrentrequests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://www.baeldung.com/spring-singleton-concurrent-requests
@SpringBootApplication
public class SpringSingletonConcurrentRequestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSingletonConcurrentRequestsApplication.class, args);
    }

}
