package property.jsoninclude;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class MyBeanTest {

  @Test
  public void whenSerializingUsingJsonInclude_thenCorrect() throws JsonProcessingException {
    MyBean bean = new MyBean(1, null);

    String result = new ObjectMapper()
        .writeValueAsString(bean);

    assertThat(result).contains("1");
    assertThat(result).doesNotContain("name");
  }
}