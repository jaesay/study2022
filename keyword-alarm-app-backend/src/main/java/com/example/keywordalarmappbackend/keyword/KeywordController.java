package com.example.keywordalarmappbackend.keyword;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("keywords")
@RestController
@RequiredArgsConstructor
public class KeywordController {

  private final KeywordService keywordService;

  @GetMapping
  public ResponseEntity<List<Keyword>> getKeywords(@AuthenticationPrincipal String userId) {
    List<Keyword> keywords = keywordService.getKeywords(Long.parseLong(userId));
    return ResponseEntity.ok(keywords);
  }

  @PostMapping
  public ResponseEntity<Keyword> registerKeyword(@RequestBody RegisterKeywordCommand command) {
    Keyword result = keywordService.registerKeyword(1L, command);
    return ResponseEntity.ok(result);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> editKeyword(@PathVariable long id, @RequestBody EditKeywordCommand command) {
    keywordService.editKeyword(id, command);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteKeyword(@PathVariable long id) {
    keywordService.deleteKeyword(id);
    return ResponseEntity.noContent().build();
  }
}
