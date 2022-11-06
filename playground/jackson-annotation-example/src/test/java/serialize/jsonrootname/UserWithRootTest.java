package serialize.jsonrootname;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

class UserWithRootTest {

  @Test
  void whenSerializingUsingJsonRootName_thenCorrect() throws JsonProcessingException {
    UserWithRoot user = new UserWithRoot(1, "John");

    String result = new ObjectMapper()
        .enable(SerializationFeature.WRAP_ROOT_VALUE)
        .writeValueAsString(user);

    assertThat(result).isEqualTo("{\"user\":{\"id\":1,\"name\":\"John\"}}");
  }
}