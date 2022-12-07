package com.example.keywordalarmappbackend.notification;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import com.example.keywordalarmappbackend.keyword.KeywordEntity;
import com.example.keywordalarmappbackend.keyword.KeywordRepository;
import com.example.keywordalarmappbackend.promotion.PromotionEntity;
import com.example.keywordalarmappbackend.promotion.PromotionSendLogEntity;
import com.example.keywordalarmappbackend.promotion.PromotionSendLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class DemoNotificationService implements NotificationService {

  private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
  private final KeywordRepository keywordRepository;
  private final PromotionSendLogRepository promotionSendLogRepository;

  @Override
  @Transactional
  public void sendMessages() {
    Map<String, List<String>> titleToMemberIdMap = keywordRepository.findAll()
        .stream()
        .collect(
            groupingBy(KeywordEntity::getTitle,
                mapping(k -> String.valueOf(k.getTitle()), toList()))
        );

    // 유저별 중복 발송 방지 Map 만들기..
    // 한개의 프로모션에 여러개의 키워드가 등록되어 있을 수 았어 프로모션은 Set 관리..
    // 현재는 프로모션 크롤링시 이미지 기반으로 키워드를 추출하지 못해 로직이 복잡..
    Map<String, Set<PromotionEntity>> memberIdToPromotionMap = new HashMap<>();
    // 새로 생성된 프로모션들
    List<PromotionSendLogEntity> sendLogs = promotionSendLogRepository.findAllBySentAtIsNull();
    for (PromotionSendLogEntity sendLog : sendLogs) {
      for (String keyword : titleToMemberIdMap.keySet()) {
        if (sendLog.getPromotion().getTitle().contains(keyword)) {
          for (String memberId : titleToMemberIdMap.get(keyword)) {
            Set<PromotionEntity> memberPromotionSet = memberIdToPromotionMap.getOrDefault(memberId, new HashSet<>());
            memberPromotionSet.add(sendLog.getPromotion());
            memberIdToPromotionMap.put(memberId, memberPromotionSet);
          }
        }
      }
    }

    memberIdToPromotionMap.forEach((memberId, promotions) -> {
      OAuth2AccessToken accessToken =
          oAuth2AuthorizedClientService.loadAuthorizedClient("kakao", memberId).getAccessToken();
      promotions.forEach(promotion -> {
        SendMessageRequest request = new SendMessageRequest(promotion.getTitle());
        WebClient webClient = WebClient.builder().baseUrl("https://kapi.kakao.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build();

        String requestJson = null;
        try {
          requestJson = new ObjectMapper().writeValueAsString(request);
        } catch (JsonProcessingException e) {
          throw new RuntimeException();
        }
        webClient.post()
            .uri("/v2/api/talk/memo/default/send")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
            .body(BodyInserters.fromFormData("template_object", requestJson))
            .retrieve()
            .toBodilessEntity()
            .block();
      });
    });

    LocalDateTime now = LocalDateTime.now();
    sendLogs.forEach(log -> log.setSentAt(now));
  }
}
