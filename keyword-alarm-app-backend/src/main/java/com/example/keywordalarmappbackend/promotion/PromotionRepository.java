package com.example.keywordalarmappbackend.promotion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
  List<PromotionEntity> findAllByTitleContaining(String keyword);
}
