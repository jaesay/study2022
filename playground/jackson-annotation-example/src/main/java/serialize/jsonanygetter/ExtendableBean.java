package serialize.jsonanygetter;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.HashMap;
import java.util.Map;

public class ExtendableBean {
  private String name;
  private Map<String, String> properties;

  public ExtendableBean(String name) {
    this.name = name;
    properties = new HashMap<>();
  }

  @JsonAnyGetter
  public Map<String, String> getProperties() {
    return properties;
  }

  public void add(String key, String value) {
    properties.put(key, value);
  }
}
