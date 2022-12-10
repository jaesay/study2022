package com.example.keywordalarmappbackend.promotion;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Promotion {
  private Long id;
  private String contentId;
  private String category;
  private String type;
  private String title;

  private String link;
  private LocalDateTime contentCreatedAt;

  public static Promotion from(PromotionEntity entity) {
    Promotion promotion = new Promotion();
    promotion.id = entity.getId();
    promotion.contentId = entity.getContentId();
    promotion.category = entity.getCategory();
    promotion.type = entity.getType();
    promotion.title = entity.getTitle();
    promotion.contentCreatedAt = entity.getContentCreatedAt();
    return promotion;
  }
}
