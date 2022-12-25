package com.example.inflearnthejavatest._03_testcontainers._26_tc_feat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.example.inflearnthejavatest.Member;
import com.example.inflearnthejavatest.MemberService;
import com.example.inflearnthejavatest.Study;
import com.example.inflearnthejavatest.StudyRepository;
import com.example.inflearnthejavatest.StudyService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
class StudyServiceTest {

  @Mock MemberService memberService;
  @Autowired StudyRepository studyRepository;

  @Container
  static GenericContainer<?> postgreSQLContainer = new GenericContainer<>("postgres")
      .withExposedPorts(5432)
      .withEnv("POSTGRES_DB", "studytest");

  @BeforeAll
  static void beforeAll() {
    Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
    postgreSQLContainer.followOutput(logConsumer); // log streaming
  }

  @BeforeEach
  void beforeEach() {
    System.out.println(postgreSQLContainer.getFirstMappedPort());
    studyRepository.deleteAll();
  }

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