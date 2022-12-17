package com.example.configpropertiesexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ConfigPropertiesExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigPropertiesExampleApplication.class, args);
  }

}
