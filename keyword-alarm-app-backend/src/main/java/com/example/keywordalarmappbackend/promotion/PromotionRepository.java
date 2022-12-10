package com.example.keywordalarmappbackend.promotion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
  List<PromotionEntity> findAllByTitleContaining(String keyword);

  Optional<PromotionEntity> findByContentId(String contentId);

  List<PromotionEntity> findByIsSent(boolean isSent);
}
