package com.example.discountalarmappbackend.keyword;

import lombok.Getter;

@Getter
public class RegisterKeywordCommandResult {
  private final long id;
  private final String name;

  public RegisterKeywordCommandResult(long id, String name) {
    this.id = id;
    this.name = name;
  }
}
