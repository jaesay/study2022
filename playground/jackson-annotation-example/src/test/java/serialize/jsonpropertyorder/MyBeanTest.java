package serialize.jsonpropertyorder;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class MyBeanTest {

  @Test
  void whenSerializingUsingJsonPropertyOrder_thenCorrect() throws JsonProcessingException {
    MyBean bean = new MyBean(1, "My bean");

    String result = new ObjectMapper().writeValueAsString(bean);

    assertThat(result).isEqualTo("{\"name\":\"My bean\",\"id\":1}");
  }
}