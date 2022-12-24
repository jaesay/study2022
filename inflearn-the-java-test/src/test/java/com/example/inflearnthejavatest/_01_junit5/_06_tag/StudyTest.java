package com.example.inflearnthejavatest._01_junit5._06_tag;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.inflearnthejavatest.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

  @Test
  @DisplayName("스터디 만들기 \uD83D\uDE00")
  @Tag("fast")
  void create_new_study() {
    Study study = new Study(10);
    assertThat(study.getLimit()).isGreaterThan(0);
  }

  @Test
  @Tag("slow")
  void create_new_study_again() {
    System.out.println("test2");
  }
}