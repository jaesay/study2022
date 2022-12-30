package com.example.inflearnthejavatest._02_mockito._21_mock_verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.inflearnthejavatest.domain.Member;
import com.example.inflearnthejavatest.member.MemberService;
import com.example.inflearnthejavatest.domain.Study;
import com.example.inflearnthejavatest.study.StudyRepository;
import com.example.inflearnthejavatest.study.StudyService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

  @Mock MemberService memberService;
  @Mock StudyRepository studyRepository;

  @Test
  void createStudyService() {
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    Member member = new Member();
    member.setId(1L);
    member.setEmail("jaesay3153@gmail.com");

    Study study = new Study(10, "테스트");

    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    when(studyRepository.save(study)).thenReturn(study);

    studyService.createNewStudy(1L, study);

    assertEquals(member.getId(), study.getOwnerId());

    verify(memberService, times(1)).notify(study);
    verify(memberService, never()).validate(any());

    InOrder inOrder = inOrder(memberService);
    inOrder.verify(memberService).notify(study);
    inOrder.verify(memberService).notify(member);
  }
}