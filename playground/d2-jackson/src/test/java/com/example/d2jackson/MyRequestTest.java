package com.example.d2jackson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {JacksonAutoConfiguration.class, JacksonConfig.class})
class MyRequestTest {

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void test() throws JsonProcessingException {
    final String json = """
        {
          "userType":"000A"
        }
        """;

    final MyRequest myRequest = objectMapper.readValue(json, MyRequest.class);
    assertThat(myRequest.getUserType()).isEqualTo(UserType.ADMIN);
  }
}