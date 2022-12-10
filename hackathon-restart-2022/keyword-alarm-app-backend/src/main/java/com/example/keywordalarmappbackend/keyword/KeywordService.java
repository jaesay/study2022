package com.example.keywordalarmappbackend.keyword;

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
        .map(e -> new Keyword(e.getId(), e.getMemberId(), e.getKeyword()))
        .collect(Collectors.toList());
  }

  @Transactional
  public Keyword registerKeyword(long memberId, RegisterKeywordCommand command) {
    UserKeywordEntity entity = UserKeywordEntity.create(memberId, command.getKeyword());
    keywordRepository.save(entity);
    return new Keyword(entity.getId(), entity.getMemberId(), entity.getKeyword());
  }

  @Transactional
  public void editKeyword(long keywordId, EditKeywordCommand command) {
    UserKeywordEntity entity = keywordRepository.findById(keywordId)
        .orElseThrow(() -> new RuntimeException("Keyword Not Found"));
    entity.editKeyword(command.getKeyword());
  }

  @Transactional
  public void deleteKeyword(long keywordId) {
    keywordRepository.deleteById(keywordId);
  }
}
