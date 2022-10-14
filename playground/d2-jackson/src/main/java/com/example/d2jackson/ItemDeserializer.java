package com.example.d2jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class ItemDeserializer extends StdDeserializer<Item> {

  public ItemDeserializer() {
    this(null);
  }

  public ItemDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Item deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {

    JsonNode node = p.getCodec().readTree(p);
    int id = (Integer) node.get("id").numberValue();
    String itemName = node.get("itemName").asText();
    int userId = (Integer) node.get("createdBy").numberValue();

    return new Item(id, itemName, new User(userId, null));
  }
}
