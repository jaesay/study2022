package com.example.configpropertiesexample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = {"classpath:configprops-test.properties"})
class ConfigPropertiesExampleApplicationTests {

  @Autowired
  private ConfigProperties configProperties;

  @Test
  public void whenSimplePropertyQueriedthenReturnsProperty() {
    assertThat(configProperties.getFrom()).isNotNull();
  }

  @Test
  public void whenListPropertyQueriedthenReturnsProperty() {
    assertThat(configProperties.getDefaultRecipients()).hasSize(2);
  }

  @Test
  public void whenMapPropertyQueriedthenReturnsProperty() {
    assertThat(configProperties.getAdditionalHeaders()).isNotNull();
    assertThat(configProperties.getAdditionalHeaders()).hasSize(3);
  }

  @Test
  public void whenObjectPropertyQueriedthenReturnsProperty() {
    assertThat(configProperties.getCredentials()).isNotNull()
        .satisfies(credentials -> {
          assertThat(credentials.getAuthMethod()).isEqualTo("SHA1");
          assertThat(credentials.getUsername()).isEqualTo("john");
          assertThat(credentials.getPassword()).isEqualTo("password");
        });
  }
}
