package com.example.discountalarmappbackend.keyword;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("keywords")
@RestController
@RequiredArgsConstructor
public class KeywordController {

  private final KeywordService keywordService;

  @GetMapping
  public ResponseEntity<List<Keyword>> getKeywords(@RequestParam long memberId) {
    List<Keyword> keywords = keywordService.getKeywords(memberId);
    return ResponseEntity.ok(keywords);
  }

  @PostMapping
  public ResponseEntity<Keyword> registerKeyword(@RequestBody RegisterKeywordCommand command) {
    Keyword result = keywordService.registerKeyword(1L, command);
    return ResponseEntity.ok(result);
  }
}
