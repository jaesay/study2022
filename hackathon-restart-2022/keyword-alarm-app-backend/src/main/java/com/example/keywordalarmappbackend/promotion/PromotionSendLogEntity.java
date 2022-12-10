//package com.example.keywordalarmappbackend.promotion;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.hibernate.Hibernate;
//
//@Table(name = "tb_promotion_send_log")
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//@ToString
//public class PromotionSendLogEntity {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
//
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "fk_promotion_id")
//  @ToString.Exclude
//  private PromotionEntity promotion;
//
//  private LocalDateTime sentAt;
//
//  public static PromotionSendLogEntity create(PromotionEntity promotion) {
//    PromotionSendLogEntity entity = new PromotionSendLogEntity();
//    entity.promotion = promotion;
//    return entity;
//  }
//
//  public void setSentAt(LocalDateTime sentAt) {
//    this.sentAt = sentAt;
//  }
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
//      return false;
//    }
//    PromotionSendLogEntity that = (PromotionSendLogEntity) o;
//    return id != null && Objects.equals(id, that.id);
//  }
//
//  @Override
//  public int hashCode() {
//    return getClass().hashCode();
//  }
//}
