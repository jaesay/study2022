package com.example.inflearnthejavatest._01_junit5._09_test_instance;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
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

  @Test
  void test() {
    System.out.println(this); // test2 와 같은 해시값
    System.out.println(value++);
  }

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