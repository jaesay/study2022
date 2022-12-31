package com.example.customerservice;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {
  private final CustomerRepository customerRepository;

  @GetMapping("/customers")
  public Collection<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }
}
