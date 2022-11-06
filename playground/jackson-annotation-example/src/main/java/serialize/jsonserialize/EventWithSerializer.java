package serialize.jsonserialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;

public class EventWithSerializer {
  private String name;

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date eventDate;

  public EventWithSerializer(String name, Date eventDate) {
    this.name = name;
    this.eventDate = eventDate;
  }
}
