package com.example.configpropertiesexample;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.unit.DataSize;

@SpringBootTest
@TestPropertySource("classpath:conversion.properties")
class PropertyConversionTest {

  @Autowired
  private PropertyConversion properties;

  @Test
  public void whenUseTimeUnitPropertyConversion_thenSuccess() {
    assertThat(properties.getTimeInDefaultUnit()).isEqualTo(Duration.ofMillis(10));
    assertThat(properties.getTimeInNano()).isEqualTo(Duration.ofNanos(9));
    assertThat(properties.getTimeInDays()).isEqualTo(Duration.ofDays(2));
  }

  @Test
  public void whenUseDataSizePropertyConversion_thenSuccess() {
    assertThat(properties.getSizeInDefaultUnit()).isEqualTo(DataSize.ofBytes(300));
    assertThat(properties.getSizeInGB()).isEqualTo(DataSize.ofGigabytes(2));
    assertThat(properties.getSizeInTB()).isEqualTo(DataSize.ofTerabytes(4));
  }

  @Test
  public void whenUseCustomPropertyConverter_thenSuccess() {
    assertThat(properties.getEmployee().getName()).isEqualTo("john");
    assertThat(properties.getEmployee().getSalary()).isEqualTo(2000.0);
  }
}