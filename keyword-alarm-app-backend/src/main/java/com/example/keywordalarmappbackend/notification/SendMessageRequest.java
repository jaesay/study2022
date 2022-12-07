package com.example.keywordalarmappbackend.notification;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMessageRequest {
  private final String objectType;
  private final String text;
  private final Link link;
  private final String buttonTitle;

  public SendMessageRequest(String text) {
    this.objectType = "text";
    this.text = text;
    this.link = new Link("https://developers.kakao.com", "https://developers.kakao.com");
    this.buttonTitle = "바로 확인";
  }

  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class Link {
    private final String webUrl;
    private final String mobileWebUrl;

    public Link(String webUrl, String mobileWebUrl) {
      this.webUrl = webUrl;
      this.mobileWebUrl = mobileWebUrl;
    }
  }
}
