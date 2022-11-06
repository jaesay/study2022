package serialize.jsonrootname;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "user")
public class UserWithRoot {
  private int id;
  private String name;

  public UserWithRoot(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
