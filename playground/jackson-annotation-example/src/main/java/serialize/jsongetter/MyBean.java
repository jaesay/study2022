package serialize.jsongetter;

import com.fasterxml.jackson.annotation.JsonGetter;

public class MyBean {
  private int id;
  private String name;

  public MyBean(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @JsonGetter("name")
  public String getTheName() {
    return name;
  }
}
