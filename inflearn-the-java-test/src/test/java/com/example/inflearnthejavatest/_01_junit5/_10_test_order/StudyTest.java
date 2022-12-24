package com.example.inflearnthejavatest._01_junit5._10_test_order;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

  int value = 1;

  // 각 메소드마다 static 하게 공유할 필요할 필요가 없기 떄문에 static 이 없어도 된다.
  @BeforeAll
  void beforeAll() {
    System.out.println("beforeAll");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("beforeEach");
  }

  @Order(1)
  @Test
  void test() {
    System.out.println(this); // test2 와 같은 해시값
    System.out.println(value++);
  }

  @Order(2)
  @Test
  void test2() {
    System.out.println(this);
    System.out.println(value++);
  }

  @AfterEach
  void afterEach() {
    System.out.println("afterEach");
  }

  @AfterAll
  void afterAll() {
    System.out.println("afterAll");
  }
}