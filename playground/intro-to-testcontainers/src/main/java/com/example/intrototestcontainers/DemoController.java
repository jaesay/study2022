package com.example.intrototestcontainers;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
  private final StringRedisTemplate stringRedisTemplate;

  private final DemoService demoService;

  public DemoController(StringRedisTemplate stringRedisTemplate, DemoService demoService) {
    this.stringRedisTemplate = stringRedisTemplate;
    this.demoService = demoService;
  }

  @GetMapping("/foo")
  public String get() {
    return stringRedisTemplate.opsForValue().get("foo");
  }

  @PutMapping("/foo")
  public void set(@RequestBody String value) {
    stringRedisTemplate.opsForValue().set("foo", value);
  }

  @GetMapping("/{id}")
  public DemoEntity getDemoEntity(@PathVariable("id") Long id) {
    return demoService.getDemoEntity(id);
  }
}
