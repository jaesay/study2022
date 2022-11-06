package deserialize.jsondeserialize;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;

public class EventWithSerializer {
  private String name;

  @JsonDeserialize(using = CustomDateDeserializer.class)
  private Date eventDate;

  public String getName() {
    return name;
  }

  public Date getEventDate() {
    return eventDate;
  }
}
