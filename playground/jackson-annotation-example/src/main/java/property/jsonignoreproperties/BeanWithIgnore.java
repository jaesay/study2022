package property.jsonignoreproperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "id" })
//@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanWithIgnore {
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
