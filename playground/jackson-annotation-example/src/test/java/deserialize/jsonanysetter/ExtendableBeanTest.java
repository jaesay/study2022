package deserialize.jsonanysetter;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ExtendableBeanTest {

  @Test
  void whenDeserializingUsingJsonAnySetter_thenCorrect() throws IOException {
    String json = "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";

    ExtendableBean bean = new ObjectMapper()
        .readerFor(ExtendableBean.class)
        .readValue(json);

    assertThat(bean.getName()).isEqualTo("My bean");
    assertThat(bean.getProperties()).isNotNull().satisfies(p -> {
      assertThat(p.get("attr1")).isEqualTo("val1");
      assertThat(p.get("attr2")).isEqualTo("val2");
    });
  }
}