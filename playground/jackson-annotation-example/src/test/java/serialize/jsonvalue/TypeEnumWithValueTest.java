package serialize.jsonvalue;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class TypeEnumWithValueTest {

  @Test
  void whenSerializingUsingJsonValue_thenCorrect() throws IOException {
    String enumAsString = new ObjectMapper().writeValueAsString(TypeEnumWithValue.TYPE1);

    assertThat(enumAsString).isEqualTo("\"Type A\"");
  }
}