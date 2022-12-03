package com.example.discountalarmappbackend.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeywordService {

  private final KeywordRepository keywordRepository;

  @Transactional
  public RegisterKeywordCommandResult registerKeyword(RegisterKeywordCommand command) {
    KeywordEntity keyword = KeywordEntity.create(1, command.getName());
    keywordRepository.save(keyword);
    return new RegisterKeywordCommandResult(keyword.getId(), keyword.getName());
  }
}
