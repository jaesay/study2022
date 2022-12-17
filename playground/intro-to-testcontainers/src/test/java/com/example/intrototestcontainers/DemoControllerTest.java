package com.example.intrototestcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

class DemoControllerTest extends AbstractionIntegrationTest {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  DemoRepository demoRepository;

  @Test
  public void simpleTest() {
    String fooResource = "/foo";

    restTemplate.put(fooResource, "bar");

    assertThat(restTemplate.getForObject(fooResource, String.class)).as("value is set").isEqualTo("bar");
  }

  @Test
  public void simpleJPATest() {
    DemoEntity demoEntity = new DemoEntity();
    demoEntity.setValue("Some value");
    demoRepository.save(demoEntity);

    DemoEntity result = restTemplate.getForObject("/" + demoEntity.getId(), DemoEntity.class);

    assertThat(result.getValue()).as("value is set").isEqualTo("Some value");
  }
}