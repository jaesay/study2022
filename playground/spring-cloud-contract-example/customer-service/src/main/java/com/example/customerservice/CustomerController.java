package com.example.customerservice;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerRepository customerRepository;

  @GetMapping("/customers")
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }
}
