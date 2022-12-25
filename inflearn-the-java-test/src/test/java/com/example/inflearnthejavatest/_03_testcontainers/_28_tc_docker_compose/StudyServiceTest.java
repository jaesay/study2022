package com.example.inflearnthejavatest._03_testcontainers._28_tc_docker_compose;

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
import java.io.File;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = StudyServiceTest.ContainerPropertyInitializer.class)
class StudyServiceTest {

  @Mock MemberService memberService;
  @Autowired StudyRepository studyRepository;

  @Value("${container.port}") int port;

  @Container
  static DockerComposeContainer<?> composeContainer =
      new DockerComposeContainer<>(new File("src/test/resources/docker-compose.yaml"))
          .withExposedService("study-db", 5432);

  @BeforeEach
  void beforeEach() {
    System.out.println(port);
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

  static class ContainerPropertyInitializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
      TestPropertyValues.of("container.port=" + composeContainer.getServicePort("study-db", 5432))
          .applyTo(context.getEnvironment());
    }
  }
}