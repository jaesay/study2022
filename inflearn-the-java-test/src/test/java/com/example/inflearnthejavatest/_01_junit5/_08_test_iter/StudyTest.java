package com.example.inflearnthejavatest._01_junit5._08_test_iter;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.inflearnthejavatest.domain.Study;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StudyTest {

  @DisplayName("스터디 만들기")
  @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
  void repeat_test(RepetitionInfo repetitionInfo) {
    System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
  void parameterized_test(String message) {
    System.out.println(message);
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} limit={0}")
  @ValueSource(ints = {10, 20, 30})
  void parameterized_test2(@ConvertWith(StudyConverter.class) Study study) {
    System.out.println(study.getLimitCount());
  }

  // 하나의 아규먼트에만 적용
  static class StudyConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
      assertThat(aClass).as("Can only convert to Study").isEqualTo(Study.class);
      return new Study(Integer.parseInt(o.toString()));
    }
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} limit={0}")
  @CsvSource({"10, '자바 스터디'", "20, 스프링"})
  void parameterized_test3(ArgumentsAccessor argumentsAccessor) {
    Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
    System.out.println(study);
  }

  @DisplayName("스터디 만들기")
  @ParameterizedTest(name = "{index} {displayName} limit={0}")
  @CsvSource({"10, '자바 스터디'", "20, 스프링"})
  void parameterized_test4(@AggregateWith(StudyAggregator.class) Study study) {
    System.out.println(study);
  }

  // inner static 이거나 public class 여야 한다.
  // 여러개의 아규먼트 변환 시 사용
  static class StudyAggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
      return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
    }
  }

  @BeforeAll
  static void beforeAll() {
    System.out.println("beforeAll");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("beforeEach");
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