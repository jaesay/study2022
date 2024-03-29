package com.example.inflearnthejavatest._02_mockito._22_bdd_mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.inflearnthejavatest.domain.Member;
import com.example.inflearnthejavatest.member.MemberService;
import com.example.inflearnthejavatest.domain.Study;
import com.example.inflearnthejavatest.study.StudyRepository;
import com.example.inflearnthejavatest.study.StudyService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

  @Mock MemberService memberService;
  @Mock StudyRepository studyRepository;

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
    given(studyRepository.save(study)).willReturn(study);

    // When
    studyService.createNewStudy(1L, study);

    // Then
    assertEquals(member.getId(), study.getOwnerId());
    then(memberService).should(times(1)).notify(study);
  }
}