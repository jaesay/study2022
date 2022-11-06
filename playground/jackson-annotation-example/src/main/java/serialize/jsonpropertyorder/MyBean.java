package serialize.jsonpropertyorder;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "id" })
//@JsonPropertyOrder(alphabetic = true)
public class MyBean {
  private int id;
  private String name;

  public MyBean(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
