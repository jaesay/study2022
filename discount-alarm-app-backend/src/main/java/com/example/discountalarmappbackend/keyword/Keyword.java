package com.example.discountalarmappbackend.keyword;

import lombok.Getter;

@Getter
public class Keyword {
  private final Long id;
  private final long memberId;
  private final String title;

  public Keyword(Long id, long memberId, String title) {
    this.id = id;
    this.memberId = memberId;
    this.title = title;
  }
}
