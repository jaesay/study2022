package com.example.d2jackson;

public enum UserType implements CodeEnum {
  ADMIN("000A"),
  USER("000U");

  private final String code;

  UserType(String code) {
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }
}
