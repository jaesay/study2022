//package com.example.keywordalarmappbackend.promotion;
//
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("promotions")
//@RestController
//@RequiredArgsConstructor
//public class PromotionController {
//
//  private final PromotionService promotionService;
//
//  @GetMapping
//  public ResponseEntity<List<Promotion>> getKeywords(@AuthenticationPrincipal String memberId) {
//    List<Promotion> promotions = promotionService.getPromotions(Long.parseLong(memberId));
//    return ResponseEntity.ok(promotㅁions);
//  }
//}
