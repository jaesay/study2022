package general.jsonformat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class EventWithFormat {
  private String name;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "dd-MM-yyyy hh:mm:ss")
  private Date eventDate;

  public EventWithFormat(String name, Date eventDate) {
    this.name = name;
    this.eventDate = eventDate;
  }

  public String getName() {
    return name;
  }

  public Date getEventDate() {
    return eventDate;
  }
}
