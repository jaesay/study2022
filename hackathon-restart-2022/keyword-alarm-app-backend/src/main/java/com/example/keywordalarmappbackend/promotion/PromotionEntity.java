package com.example.keywordalarmappbackend.promotion;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

@Table(name = "tb_promotion")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class PromotionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 컨텐트 ID
   * 원본 컨텐트 ID
   */
  private String contentId;

  private String category;

  private String type;

  private String title;

  /**
   * 컨텐트 ID
   * 원본 컨텐트 등록 일자
   */
  private LocalDateTime contentCreatedAt;

  @Column(name = "is_sent")
  private Boolean isSent = false;

  public static PromotionEntity create(String contentId, String category, String type, String title, LocalDateTime contentCreatedAt) {
    PromotionEntity entity = new PromotionEntity();
    entity.contentId = contentId;
    entity.category = category;
    entity.type = type;
    entity.title = title;
    entity.contentCreatedAt = contentCreatedAt;
    entity.isSent = false;
    return entity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    PromotionEntity that = (PromotionEntity) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void setWasSent() {
    this.isSent = true;
  }
}
