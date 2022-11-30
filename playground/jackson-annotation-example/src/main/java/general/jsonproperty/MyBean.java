package general.jsonproperty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyBean {
  private int id;
  private String name;

  private MyBean() {
  }

  public MyBean(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @JsonProperty("name")
  public void setTheName(String name) {
    this.name = name;
  }

  @JsonProperty("name")
  public String getTheName() {
    return name;
  }

  public int getId() {
    return id;
  }
}
