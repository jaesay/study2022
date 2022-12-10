package com.example.keywordalarmappbackend.promotion;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DemoPromotionService implements PromotionService {

  private final PromotionRepository promotionRepository;

  public Optional<PromotionEntity> findByContentId(String contentId) {
    return this.promotionRepository.findByContentId(contentId);
  }

  @Async
  @Transactional
  public void asyncSaveIfContentIdIsNotExists(List<PromotionEntity> promotions) {

    for (var promotion: promotions) {
      var contentId = promotion.getContentId();
      if (findByContentId(contentId).isEmpty()) {
        this.promotionRepository.save(promotion);
        log.debug("promotion {} was skipped, already exists.", promotion.getContentId());
      }
    }
  }

  @Override
  @Transactional
  public void setWasSent(Long id) {
    var promotion = this.promotionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("failed to find promotion by id, " + id));
    promotion.setWasSent();
    this.promotionRepository.save(promotion);
  }

  @Override
  public List<PromotionEntity> findByIsSent(boolean b) {
    return this.promotionRepository.findByIsSent(b);
  }
}
