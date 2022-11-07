package property.jsonignoretype;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class User {
  private int id;
  private Name name;

  public User(int id, Name name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public Name getName() {
    return name;
  }

  @JsonIgnoreType
  public static class Name {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }
  }
}
