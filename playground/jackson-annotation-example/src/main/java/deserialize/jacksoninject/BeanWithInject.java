package deserialize.jacksoninject;

import com.fasterxml.jackson.annotation.JacksonInject;

public class BeanWithInject {
  @JacksonInject
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
