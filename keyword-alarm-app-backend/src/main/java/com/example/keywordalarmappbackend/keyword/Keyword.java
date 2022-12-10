package com.example.keywordalarmappbackend.keyword;

import lombok.Getter;

@Getter
public class Keyword {
  private final Long id;
  private final long memberId;
  private final String keyword;

  public Keyword(Long id, long memberId, String keyword) {
    this.id = id;
    this.memberId = memberId;
    this.keyword = keyword;
  }
}
