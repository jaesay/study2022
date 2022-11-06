package serialize.jsonrawvalue;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class RawBean {
  private String name;

  @JsonRawValue
  private String json;

  public RawBean(String name, String json) {
    this.name = name;
    this.json = json;
  }
}
