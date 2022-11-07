package property.jsonautodetect;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class PrivateBeanTest {

  @Test
  public void whenSerializingUsingJsonAutoDetect_thenCorrect() throws JsonProcessingException {
    PrivateBean bean = new PrivateBean(1, "My bean");

    String result = new ObjectMapper()
        .writeValueAsString(bean);

    assertThat(result).contains("1");
    assertThat(result).contains("My bean");
  }
}