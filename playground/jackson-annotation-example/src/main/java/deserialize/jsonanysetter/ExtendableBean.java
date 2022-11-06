package deserialize.jsonanysetter;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class ExtendableBean {
  private String name;
  private Map<String, String> properties;

  public ExtendableBean() {
    properties = new HashMap<String, String>();
  }

  @JsonAnySetter
  public void add(String key, String value) {
    properties.put(key, value);
  }

  public String getName() {
    return name;
  }

  public Map<String, String> getProperties() {
    return properties;
  }
}
