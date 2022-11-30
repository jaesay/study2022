package general.jsonunwrapped;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class UnwrappedUserTest {

  @Test
  void whenSerializingUsingJsonUnwrapped_thenCorrect() throws JsonProcessingException {
    UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
    UnwrappedUser user = new UnwrappedUser(1, name);

    String result = new ObjectMapper().writeValueAsString(user);

    assertThat(result).contains("John");
    assertThat(result).doesNotContain("name");
  }
}