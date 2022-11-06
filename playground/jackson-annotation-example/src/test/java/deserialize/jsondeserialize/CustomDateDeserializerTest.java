package deserialize.jsondeserialize;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;

class CustomDateDeserializerTest {

  @Test
  void whenDeserializingUsingJsonDeserialize_thenCorrect() throws IOException {
    String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    EventWithSerializer event = new ObjectMapper()
        .readerFor(EventWithSerializer.class)
        .readValue(json);

    assertThat(df.format(event.getEventDate())).isEqualTo("20-12-2014 02:30:00");
  }
}