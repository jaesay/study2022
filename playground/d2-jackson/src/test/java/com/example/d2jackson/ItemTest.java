package com.example.d2jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ItemTest {

  @Test
  void test() throws JsonProcessingException {
    final String json = """
        {
             "id": 1,
             "itemName": "theItem",
             "createdBy": 2
         }""";

    final Item itemWithOwner = new ObjectMapper().readValue(json, Item.class);
    System.out.println(itemWithOwner);
  }
}