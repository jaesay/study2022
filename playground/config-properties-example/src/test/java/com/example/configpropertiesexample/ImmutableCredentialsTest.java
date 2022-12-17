package com.example.configpropertiesexample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:configprops-test.properties")
class ImmutableCredentialsTest {

  @Autowired
  private ImmutableCredentials immutableCredentials;

  @Test
  public void whenConstructorBindingUsed_thenPropertiesCorrectlyBound() {
    assertThat(immutableCredentials.getAuthMethod()).isEqualTo("SHA1");
    assertThat(immutableCredentials.getUsername()).isEqualTo("john");
    assertThat(immutableCredentials.getPassword()).isEqualTo("password");
  }
}