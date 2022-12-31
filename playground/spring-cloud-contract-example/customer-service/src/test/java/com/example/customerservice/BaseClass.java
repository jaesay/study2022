package com.example.customerservice;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class BaseClass {

  @Autowired
  private CustomerRestController customerRestController;

  @MockBean
  private CustomerRepository customerRepository;

  @BeforeEach
  void beforeEach() {
    Mockito.when(this.customerRepository.findAll())
        .thenReturn(Arrays.asList(new Customer(1L, "Jane"), new Customer(2L, "Bob")));

    RestAssuredMockMvc.standaloneSetup(this.customerRestController);
  }

}
