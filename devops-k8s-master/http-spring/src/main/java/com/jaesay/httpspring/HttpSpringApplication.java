package com.jaesay.httpspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@SpringBootApplication
@RestController
public class HttpSpringApplication {

    @GetMapping("/hello/ingress")
    public String helloIngress() {
        return "Hello, ingress";
    }

    @GetMapping("/")
    public String home() {
        String hostName = System.getenv("HOSTNAME");

        if(hostName == null || hostName.isEmpty()) {
            try {
                hostName = InetAddress.getLocalHost().getHostName();
            } catch (Exception e) {
                hostName = "Unknown";
            }
        }
        return "Hello, " + hostName + " v1";
    }

    public static void main(String[] args) {
        SpringApplication.run(HttpSpringApplication.class, args);
    }

}
