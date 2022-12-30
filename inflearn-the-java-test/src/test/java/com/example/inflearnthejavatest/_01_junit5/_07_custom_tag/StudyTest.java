package com.example.inflearnthejavatest._01_junit5._07_custom_tag;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.inflearnthejavatest.domain.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

  @DisplayName("스터디 만들기 \uD83D\uDE00")
  @FastTest
  void create_new_study() {
    Study study = new Study(10);
    assertThat(study.getLimitCount()).isGreaterThan(0);
  }

  @SlowTest
  void create_new_study_again() {
    System.out.println("test2");
  }
}