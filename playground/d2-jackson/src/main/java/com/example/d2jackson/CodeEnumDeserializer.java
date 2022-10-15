package com.example.d2jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class CodeEnumDeserializer extends StdDeserializer<Enum<? extends CodeEnum>> implements
    ContextualDeserializer {

  public CodeEnumDeserializer() {
    this(null);
  }

  protected CodeEnumDeserializer(Class<?> vc) {
    super(vc);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Enum<? extends CodeEnum> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode jsonNode = jp.getCodec().readTree(jp);
    String text = jsonNode.asText();
    Class<? extends Enum> enumType = (Class<? extends Enum>) this._valueClass;
    Enum.valueOf(enumType, enumType.cast(enumType.getEnumConstants()[0]).name());
    for (Enum enumConstant : enumType.getEnumConstants()) {
      if (((CodeEnum) enumConstant).getCode().equals(text)) {
        return Enum.valueOf(enumType, enumConstant.name());
      }
    }

    throw new RuntimeException();
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    return new CodeEnumDeserializer(property.getType().getRawClass());
  }
}
