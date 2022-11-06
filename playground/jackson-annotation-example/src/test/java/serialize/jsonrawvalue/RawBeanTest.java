package serialize.jsonrawvalue;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class RawBeanTest {

  @Test
  void whenSerializingUsingJsonRawValue_thenCorrect() throws JsonProcessingException {
    RawBean bean = new RawBean("My bean", "{\"attr\":false}");

    String result = new ObjectMapper().writeValueAsString(bean);

    assertThat(result).contains("My bean");
    assertThat(result).contains("{\"attr\":false}");
  }
}