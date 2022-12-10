package com.example.keywordalarmappbackend.crawler.blossom;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import com.example.keywordalarmappbackend.crawler.blossom.Property.SeleniumConfig;
import com.example.keywordalarmappbackend.crawler.blossom.Property.Url;
import com.example.keywordalarmappbackend.promotion.PromotionEntity;
import com.example.keywordalarmappbackend.promotion.PromotionService;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlossomCrawler {

  private final PromotionService promotionService;

  private final Property prop;

  @Scheduled(initialDelay = 1000, fixedDelay = 10000)
  public void crawling() {

    log.info("[schedule] start to crawling");

    System.setProperty(SeleniumConfig.WEB_DRIVER_ID, SeleniumConfig.WEB_DRIVER_PATH);

    ChromeOptions options = new ChromeOptions();
    options.addArguments("headless");
    WebDriver driver = new ChromeDriver(options);

    driver.get(Url.LOGIN);

    var idInputBox = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(_driver -> _driver.findElement(id("txtPC_LoginID")));
    var pwInputBox = driver.findElement(id("txtPC_LoginPW"));

    idInputBox.sendKeys(prop.ID);
    pwInputBox.sendKeys(prop.PW);

    ((JavascriptExecutor) driver).executeScript("btnPC_Login_Click()");

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    var loginContinueBtn = driver.findElement(By.className("overlap_btn02"));
    if (loginContinueBtn != null) {
      loginContinueBtn.click();
    }

    driver.get(Url.MEMBER_PROMITION);
    var memberPromotionLink = driver.findElement(id("__left_folder_45044"));
    memberPromotionLink.click();

    var tbody = driver.findElement(cssSelector("#cphContent_grid tbody"));

    List<PromotionEntity> promotions = new ArrayList<>();
    for (var tr: tbody.findElements(By.xpath("./child::*"))) {
      var trChildren = tr.findElements(By.xpath("./child::*"));

      var num = trChildren.get(0).getAccessibleName();
      var category = trChildren.get(1).getAccessibleName();
      var type = trChildren.get(2).getAccessibleName();
      var title = trChildren.get(3).getAccessibleName();
      var contentCreatedAt = trChildren.get(5).getAccessibleName();

      var contentCreatedAtAsLocalDate = LocalDate.parse(contentCreatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

      log.debug("{} {} {} {} {}", num, category, type, title, contentCreatedAt);

      var promotion = PromotionEntity.create(num, category, type, title, contentCreatedAtAsLocalDate.atStartOfDay());
      promotions.add(promotion);
    }
    promotionService.asyncSaveIfContentIdIsNotExists(promotions);


    driver.close();

    log.info("[schedule] done...");
  }
}