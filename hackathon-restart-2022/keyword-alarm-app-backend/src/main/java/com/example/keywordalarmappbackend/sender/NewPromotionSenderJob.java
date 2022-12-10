package com.example.keywordalarmappbackend.sender;

import com.example.keywordalarmappbackend.promotion.PromotionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewPromotionSenderJob {

  private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
  private final PromotionService promotionService;

  private final JdbcTemplate jdbcTemplate;

  @Scheduled(initialDelay = 15000, fixedDelay = 3000)
  public void send() {
    log.info("start to promotion sending job...");
    var notBeSentPromotions = promotionService.findByIsSent(false);
    var target = findSendTargetPromotions();

    target.stream().forEach(send -> {
      sendToKakao(send);
    });

    notBeSentPromotions.stream().forEach(pro -> {
      promotionService.setWasSent(pro.getId());
    });
    log.info("done to promotion sending job...");
  }

  private void sendToKakao(SendTargetPromotion sendTargetPromotion) {
    log.debug("신규 프로모션 키워드 매칭, 메세지 전송! promotionId {} to userId {}"
        , sendTargetPromotion.getPromotionId(), sendTargetPromotion.getMemberId());

    var authClient = oAuth2AuthorizedClientService.loadAuthorizedClient(
        "kakao", sendTargetPromotion.getMemberId().toString());

    if (authClient == null) {
      log.warn("no access token memberId, {}", sendTargetPromotion.getMemberId());
      return;
    }

    var accessToken = authClient.getAccessToken();

    SendMessageRequest request = new SendMessageRequest(sendTargetPromotion.getTitle());
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
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getTokenValue())
        .body(BodyInserters.fromFormData("template_object", requestJson))
        .retrieve()
        .toBodilessEntity()
        .block();
  }

  private List<SendTargetPromotion> findSendTargetPromotions() {
    final String sql =
        "    SELECT" +
            "    t.member_id" +
            "    , p.id as promotion_id" +
            "    , p.*" +
            "  FROM (" +
            "    SELECT" +
            "      member_id, promotion_id" +
            "    FROM (" +
            "      SELECT k.keyword" +
            "        , k.member_id" +
            "        , p.id as promotion_id" +
            "        , p.category" +
            "        , p.type" +
            "        , p.title" +
            "      FROM tb_user_keyword k" +
            "      LEFT OUTER JOIN (" +
            "        SELECT * FROM tb_promotion WHERE is_sent = FALSE) p" +
            "      ON INSTR(p.type, k.keyword) > 0" +
            "      OR INSTR(p.category, k.keyword) > 0" +
            "      OR INSTR(p.title, k.keyword) > 0" +
            "    ) t" +
            "    group by member_id, promotion_id" +
            "  ) t" +
            "  LEFT JOIN tb_promotion p" +
            "    ON t.promotion_id = p.id";

    return jdbcTemplate.query(sql, (rs, num) ->
            new SendTargetPromotion(
                rs.getLong("member_id")
                , rs.getLong("promotion_id")
                , rs.getString("category")
                , rs.getString("type")
                , rs.getString("title")
            )).stream().filter(row -> row.getPromotionId() != 0)
        .collect(Collectors.toList());
  }

  @Getter
  @AllArgsConstructor
  static class SendTargetPromotion {
    private Long memberId;
    private Long promotionId;
    private String caetgory;
    private String type;
    private String title;
  }
}