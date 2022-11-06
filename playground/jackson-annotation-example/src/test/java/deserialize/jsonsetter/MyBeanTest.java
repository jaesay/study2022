package deserialize.jsonsetter;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class MyBeanTest {

  @Test
  void whenDeserializingUsingJsonSetter_thenCorrect() throws IOException {
    String json = "{\"id\":1,\"name\":\"My bean\"}";

    MyBean bean = new ObjectMapper()
        .readerFor(MyBean.class)
        .readValue(json);

    assertThat(bean.getName()).isEqualTo("My bean");
  }
}