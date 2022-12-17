package com.example.intrototestcontainers;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.Startables;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    classes = IntroToTestcontainersApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT,
    properties = { "spring.datasource.url=jdbc:tc:postgresql:11-alpine:///databasename" }
)
@ActiveProfiles("test")
public abstract class AbstractionIntegrationTest {

  static GenericContainer<?> redis = new GenericContainer<>("redis:6-alpine")
      .withExposedPorts(6379);

  @DynamicPropertySource
  public static void setUpRedis(DynamicPropertyRegistry registry) {
    Startables.deepStart(redis).join();

    registry.add("spring.redis.host", redis::getHost);
    registry.add("spring.redis.port", redis::getFirstMappedPort);
  }
}
