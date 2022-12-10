package com.example.keywordalarmappbackend.crawler.blossom;

import static org.openqa.selenium.By.cssSelector;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration("cralwerConfiguration")
public class Property {

  @Value("${app.crawler.blossom.account.id}")
  public String ID;

  @Value("${app.crawler.blossom.account.pw}")
  public String PW;

  public static class SeleniumConfig {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "./chromedriver";
  }

  public static class Url {
    public static final String LOGIN = "https://blossom.shinsegae.com/Website/Login.aspx?isMobile=0";
    public static final String MEMBER_PROMITION = "http://blossom.shinsegae.com/WebSite/Basic/Board/BoardList.aspx?system=Board&fdid=1411#__left_folder_45044!!";
  }

  public static class PromotionSelector {
    public static final String NUM_SELECTOR_FORMAT = "#cphContent_grid_Row_%s_RNUM";
    public static final String CATEGORY_SELECTOR_FORMAT = "#cphContent_grid_Row_%s_CategoryPath p";
    public static final String TYPE_FORMAT = "#cphContent_grid_Row_%s_UF_Value0 a";
    public static final String TITLE_FORMAT = "#cphContent_grid_Row_%s_UF_Value1 div a";
    public static final String CREATED_AT_FORMAT = "#cphContent_grid_Row_%s_BeginDate a span";

    public static String getNum(WebElement webElement, int i) {
      var selector = cssSelector(String.format(NUM_SELECTOR_FORMAT, i));
      return webElement.findElement(selector).getText();
    }

    public static String getCategory(WebElement webElement, int i) {
      var selector = cssSelector(String.format(CATEGORY_SELECTOR_FORMAT, i));
      return webElement.findElement(selector).getText();
    }

    public static String getType(WebElement webElement, int i) {
      var selector = cssSelector(String.format(TYPE_FORMAT, i));
      return webElement.findElement(selector).getText();
    }

    public static String getTitle(WebElement webElement, int i) {
      var selector = cssSelector(String.format(TITLE_FORMAT, i));
      return webElement.findElement(selector).getText();
    }

    public static LocalDate getCreatedAt(WebElement webElement, int i) {
      var selector = cssSelector(String.format(CREATED_AT_FORMAT, i));
      var dateStr = webElement.findElement(selector).getText();
      return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
  }
}