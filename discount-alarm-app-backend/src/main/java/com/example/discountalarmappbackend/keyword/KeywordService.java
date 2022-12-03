package com.example.discountalarmappbackend.keyword;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordService {

  private final KeywordRepository keywordRepository;

  public List<Keyword> getKeywords(long memberId) {
    return keywordRepository.findAllByMemberId(memberId)
        .stream()
        .map(e -> new Keyword(e.getId(), e.getMemberId(), e.getName()))
        .collect(Collectors.toList());
  }

  @Transactional
  public Keyword registerKeyword(long memberId, RegisterKeywordCommand command) {
    KeywordEntity entity = KeywordEntity.create(memberId, command.getName());
    keywordRepository.save(entity);
    return new Keyword(entity.getId(), entity.getMemberId(), entity.getName());
  }

  @Transactional
  public Keyword editKeyword(long keywordId, EditKeywordCommand command) {
    KeywordEntity entity = keywordRepository.findById(keywordId)
        .orElseThrow(() -> new RuntimeException("Keyword Not Found"));
    entity.editName(command.getName());
    return new Keyword(entity.getId(), entity.getMemberId(), entity.getName());
  }

  @Transactional
  public void deleteKeyword(long keywordId) {
    keywordRepository.deleteById(keywordId);
  }
}
