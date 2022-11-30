package com.example.inflearnthejavatest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudyTest {

  @BeforeAll
  static void beforeAll() {
    System.out.println("beforeAll");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("beforeEach");
  }

  @Test
  void test() {
    System.out.println("test");
  }

  @Test
  void test2() {
    System.out.println("test2");
  }

  @AfterEach
  void afterEach() {
    System.out.println("afterEach");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("afterAll");
  }
}