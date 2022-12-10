package com.example.keywordalarmappbackend.promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionService {
  Optional<PromotionEntity> findByContentId(String contentId);
  void asyncSaveIfContentIdIsNotExists(List<PromotionEntity> promotions);
  void setWasSent(Long id);
  List<PromotionEntity> findByIsSent(boolean sent);
}
