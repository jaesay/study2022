package com.example.d2jackson;

public class MyRequest {
//  @JsonDeserialize(using = CodeEnumDeserializer.class)
  private UserType userType;

  public UserType getUserType() {
    return userType;
  }

  @Override
  public String toString() {
    return "MyRequest{" +
        "userType=" + userType +
        '}';
  }
}
