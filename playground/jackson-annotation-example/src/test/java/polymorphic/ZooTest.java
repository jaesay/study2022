package polymorphic;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ZooTest {

  @Test
  void whenSerializingPolymorphic_thenCorrect() throws JsonProcessingException {
    Zoo.Dog dog = new Zoo.Dog("lacy");
    Zoo zoo = new Zoo(dog);

    String result = new ObjectMapper()
        .writeValueAsString(zoo);

    assertThat(result).contains("type");
    assertThat(result).contains("dog");
  }

  @Test
  void whenDeserializingPolymorphic_thenCorrect() throws IOException {
    String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";

    Zoo zoo = new ObjectMapper()
        .readerFor(Zoo.class)
        .readValue(json);

    assertThat(zoo.getAnimal().getName()).isEqualTo("lacy");
    assertThat(zoo.getAnimal().getClass()).isEqualTo(Zoo.Cat.class);
  }

}