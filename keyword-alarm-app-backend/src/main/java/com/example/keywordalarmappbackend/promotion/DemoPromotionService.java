package com.example.keywordalarmappbackend.promotion;

import com.example.keywordalarmappbackend.keyword.KeywordEntity;
import com.example.keywordalarmappbackend.keyword.KeywordRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DemoPromotionService implements PromotionService {

  private final PromotionRepository promotionRepository;
  private final KeywordRepository keywordRepository;

  // TODO jaesay: 키워드 추출 가능 시 전환하기
  public List<Promotion> getPromotions(long memberId) {
    Set<PromotionEntity> entities = keywordRepository.findAllByMemberId(memberId)
        .stream()
        .map(KeywordEntity::getTitle)
        .map(promotionRepository::findAllByTitleContaining)
        .flatMap(List::stream)
        .collect(Collectors.toSet());

    return entities.stream()
        .map(Promotion::from)
        .collect(Collectors.toList());
  }
}
