package com.example.customerservice;

import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CustomerRestControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CustomerRepository customerRepository;

  @Test
  void shouldReturnAllCustomers() throws Exception {
    given(this.customerRepository.findAll()).willReturn(Arrays.asList(new Customer(1L, "Jane")));

    this.mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Jane"))
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }
}
