package com.example.discountalarmappbackend.keyword;

import lombok.Getter;

@Getter
public class Keyword {
  private final Long id;
  private final long memberId;
  private final String name;

  public Keyword(Long id, long memberId, String name) {
    this.id = id;
    this.memberId = memberId;
    this.name = name;
  }
}
