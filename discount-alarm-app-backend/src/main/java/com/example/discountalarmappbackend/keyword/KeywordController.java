package com.example.discountalarmappbackend.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("keywords")
@RestController
@RequiredArgsConstructor
public class KeywordController {

  private final KeywordService keywordService;

  @PostMapping
  public ResponseEntity<RegisterKeywordCommandResult> registerKeyword(@RequestBody RegisterKeywordCommand command) {
    RegisterKeywordCommandResult result = keywordService.registerKeyword(command);
    return ResponseEntity.ok(result);
  }
}
