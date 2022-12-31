package com.example.customerclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerClientConfiguration {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  CustomerClient customerClient(RestTemplate restTemplate) {
    return new CustomerClient(restTemplate);
  }
}
