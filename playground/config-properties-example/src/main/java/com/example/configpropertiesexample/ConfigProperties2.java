package com.example.configpropertiesexample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties2 {

  @Bean
  @ConfigurationProperties(prefix = "item")
  public Item item() {
    return new Item();
  }
}
