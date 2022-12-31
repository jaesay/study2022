package com.example.customerclient;

import java.util.Collection;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@SpringBootTest(classes = CustomerClientApplication.class)
//@AutoConfigureWireMock(port = 8081)
@AutoConfigureStubRunner(ids = "com.example:customer-service:+:8081", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class CustomerClientTest {

  @Autowired
  private CustomerClient customerClient;

//  @Autowired
//  private ObjectMapper objectMapper;

  @Test
  void test() throws Exception {
//    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/customers"))
//        .willReturn(
//            WireMock.aResponse()
//                .withStatus(200)
//                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .withBody(jsonForCustomer(new Customer(1L, "Jane"), new Customer(2L, "Bob")))));

    Collection<Customer> customers = this.customerClient.getAllCustomers();
    BDDAssertions.then(customers).size().isEqualTo(2);
    BDDAssertions.then(customers.iterator().next().getId()).isEqualTo(1L);
    BDDAssertions.then(customers.iterator().next().getName()).isEqualTo("Jane");
  }
//
//  private String jsonForCustomer(Customer... customers) throws Exception {
//    List<Customer> customerList = Arrays.asList(customers);
//    return this.objectMapper.writeValueAsString(customerList);
//  }

}