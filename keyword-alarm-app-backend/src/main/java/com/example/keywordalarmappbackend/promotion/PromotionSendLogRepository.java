//package com.example.keywordalarmappbackend.promotion;
//
//import java.util.List;
//import org.springframework.data.jpa.repository.EntityGraph;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface PromotionSendLogRepository
//  extends JpaRepository<PromotionSendLogEntity, Long> {
//{
//  @EntityGraph(attributePaths = {"promotion"})
//  List<PromotionSendLogEntity> findAllBySentAtIsNull();
//}
