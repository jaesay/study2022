package deserialize.jsoncreator;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class BeanWithCreatorTest {

  @Test
  void whenDeserializingUsingJsonCreator_thenCorrect() throws IOException {
    String json = "{\"id\":1,\"theName\":\"My bean\"}";

    BeanWithCreator bean = new ObjectMapper()
        .readerFor(BeanWithCreator.class)
        .readValue(json);

    assertThat("My bean").isEqualTo(bean.getName());
  }
}