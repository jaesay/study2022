package com.example.discountalarmappbackend.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeywordService {

  private final KeywordRepository keywordRepository;

  @Transactional
  public Keyword registerKeyword(long memberId, RegisterKeywordCommand command) {
    KeywordEntity entity = KeywordEntity.create(memberId, command.getName());
    keywordRepository.save(entity);
    return new Keyword(entity.getId(), entity.getMemberId(), entity.getName());
  }
}
