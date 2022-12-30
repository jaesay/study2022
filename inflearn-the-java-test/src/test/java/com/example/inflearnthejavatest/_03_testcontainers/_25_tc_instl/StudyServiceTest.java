package com.example.inflearnthejavatest._03_testcontainers._25_tc_instl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.example.inflearnthejavatest.domain.Member;
import com.example.inflearnthejavatest.member.MemberService;
import com.example.inflearnthejavatest.domain.Study;
import com.example.inflearnthejavatest.study.StudyRepository;
import com.example.inflearnthejavatest.study.StudyService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
class StudyServiceTest {

  @Mock MemberService memberService;
  @Autowired StudyRepository studyRepository;

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres")
      .withDatabaseName("studytest");

  @BeforeEach
  void beforeEach() {
    studyRepository.deleteAll();
  }

//  @BeforeAll
//  static void beforeAll() {
//    postgreSQLContainer.start();
//  }
//
//  @AfterAll
//  static void afterAll() {
//    postgreSQLContainer.stop();
//  }

  @Test
  void createStudyService() {
    // Given
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    Member member = new Member();
    member.setId(1L);
    member.setEmail("jaesay3153@gmail.com");

    Study study = new Study(10, "테스트");

    given(memberService.findById(1L)).willReturn(Optional.of(member));

    // When
    studyService.createNewStudy(1L, study);

    // Then
    assertEquals(member.getId(), study.getOwnerId());
    then(memberService).should(times(1)).notify(study);
  }
}