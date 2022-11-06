package serialize.jsonanygetter;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ExtendableBeanTest {

  @Test
  void whenSerializingUsingJsonAnyGetter_thenCorrect() throws JsonProcessingException {

    ExtendableBean bean = new ExtendableBean("My bean");
    bean.add("attr1", "val1");
    bean.add("attr2", "val2");

    String result = new ObjectMapper().writeValueAsString(bean);

    // @JsonAnyGetter 안쓸경우 "{"name":"My bean","properties":{"attr2":"val2","attr1":"val1"}}"
    assertThat(result).isEqualTo("{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}");
  }
}