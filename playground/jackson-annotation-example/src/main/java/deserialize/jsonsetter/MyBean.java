package deserialize.jsonsetter;

import com.fasterxml.jackson.annotation.JsonSetter;

public class MyBean {
  private int id;
  private String name;

  @JsonSetter("name")
  public void setTheName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
