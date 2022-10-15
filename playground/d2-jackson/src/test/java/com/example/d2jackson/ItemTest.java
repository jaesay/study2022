package com.example.d2jackson;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {JacksonAutoConfiguration.class, JacksonConfig.class})
class ItemTest {

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void test() throws JsonProcessingException {
    final String json = """
        {
             "id": 1,
             "itemName": "theItem",
             "createdBy": 2
         }""";

    final Item itemWithOwner = objectMapper.readValue(json, Item.class);
    assertThat(itemWithOwner.getOwner()).satisfies(o -> {
      assertThat(o.getId()).isEqualTo(2);
      assertThat(o.getName()).isNull();
    });
  }
}