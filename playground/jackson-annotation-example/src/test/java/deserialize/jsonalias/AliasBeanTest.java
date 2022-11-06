package deserialize.jsonalias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class AliasBeanTest {

  @Test
  void whenDeserializingUsingJsonAlias_thenCorrect() throws IOException {
    String json = "{\"fName\": \"John\", \"lastName\": \"Green\"}";

    AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);

    assertEquals("John", aliasBean.getFirstName());
  }
}