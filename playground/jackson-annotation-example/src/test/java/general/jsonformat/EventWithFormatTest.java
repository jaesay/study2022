package general.jsonformat;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;

class EventWithFormatTest {

  @Test
  void whenSerializingUsingJsonFormat_thenCorrect() throws JsonProcessingException, ParseException {
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));

    String toParse = "20-12-2014 02:30:00";
    Date date = df.parse(toParse);
    EventWithFormat event = new EventWithFormat("party", date);

    String result = new ObjectMapper().writeValueAsString(event);

    assertThat(result).contains(toParse);
  }

}