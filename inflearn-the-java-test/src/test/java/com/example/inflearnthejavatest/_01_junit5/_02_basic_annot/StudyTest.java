package com.example.inflearnthejavatest._01_junit5._02_basic_annot;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
  @Disabled
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