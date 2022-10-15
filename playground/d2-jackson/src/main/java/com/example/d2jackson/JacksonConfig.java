package com.example.d2jackson;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer objectMapperConfig() {
    return builder -> {
      builder.deserializerByType(Item.class, new ItemDeserializer());
    };
  }
}
