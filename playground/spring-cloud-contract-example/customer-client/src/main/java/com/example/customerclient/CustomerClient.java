package com.example.customerclient;

import java.util.Collection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CustomerClient {

  private final RestTemplate restTemplate;

  public CustomerClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Collection<Customer> getAllCustomers() {
    ParameterizedTypeReference<Collection<Customer>> ptr =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<Collection<Customer>> responseEntity = this.restTemplate.exchange(
        "http://localhost:8081/customers", HttpMethod.GET, null, ptr);

    return responseEntity.getBody();
  }
}
