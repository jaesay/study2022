package deserialize.jsoncreator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BeanWithCreator {
  private final int id;
  private final String name;

  @JsonCreator
  public BeanWithCreator(
      @JsonProperty("id") int id,
      @JsonProperty("theName") String name) {

    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}