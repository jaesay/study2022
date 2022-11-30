package general.jsonproperty;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class MyBeanTest {

  @Test
  void whenUsingJsonProperty_thenCorrect() throws IOException {
    MyBean bean = new MyBean(1, "My bean");

    String result = new ObjectMapper().writeValueAsString(bean);

    assertThat(result).contains("My bean");
    assertThat(result).contains("1");

    MyBean resultBean = new ObjectMapper()
        .readerFor(MyBean.class)
        .readValue(result);

    assertThat(resultBean.getTheName()).isEqualTo("My bean");
  }
}