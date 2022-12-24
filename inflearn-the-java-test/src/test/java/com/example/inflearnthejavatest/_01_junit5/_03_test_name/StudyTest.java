package com.example.inflearnthejavatest._01_junit5._03_test_name;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

  @Test
  @DisplayName("스터디 만들기 \uD83D\uDE00")
  void create_new_study() {
    System.out.println("test");
  }

  @Test
  void create_new_study_again() {
    System.out.println("test2");
  }
}