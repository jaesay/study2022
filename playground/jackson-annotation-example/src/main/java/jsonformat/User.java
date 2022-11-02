package jsonformat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class User {
  private String firstName;
  private String lastName;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdDateTime;

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.createdDateTime = LocalDateTime.now();
  }

  private User() {
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public LocalDateTime getCreatedDateTime() {
    return createdDateTime;
  }

  @Override
  public String toString() {
    return "User{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", createdDateTime=" + createdDateTime +
        '}';
  }
}
