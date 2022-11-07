package property.jsonignore;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BeanWithIgnore {
  @JsonIgnore
  private int id;
  private String name;

  public BeanWithIgnore(int id, String name) {
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
