package general.jsonunwrapped;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class UnwrappedUser {
  private int id;

  @JsonUnwrapped
  private Name name;

  public UnwrappedUser(int id, Name name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public Name getName() {
    return name;
  }

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
