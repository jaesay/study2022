package jsonignoreproperties;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class BeanWithIgnoreTest {

  @Test
  void whenSerializingUsingJsonIgnoreProperties_thenCorrect() throws JsonProcessingException {
    BeanWithIgnore bean = new BeanWithIgnore(1, "My bean");

    String result = new ObjectMapper().writeValueAsString(bean);

    assertThat(result).contains("My bean");
    assertThat(result).doesNotContain("id");
  }
}