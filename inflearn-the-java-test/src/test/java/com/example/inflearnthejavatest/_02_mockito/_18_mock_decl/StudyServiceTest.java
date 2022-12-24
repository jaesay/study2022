package com.example.inflearnthejavatest._02_mockito._18_mock_decl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import com.example.inflearnthejavatest.MemberService;
import com.example.inflearnthejavatest.StudyRepository;
import com.example.inflearnthejavatest.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // @Mock 사용 시 필요
class StudyServiceTest {

//  MemberService memberService = mock(MemberService.class);
//  StudyRepository studyRepository = mock(StudyRepository.class);

//  @Mock MemberService memberService;
//  @Mock StudyRepository studyRepository;

  @Test
  void createStudyService(@Mock MemberService memberService,
      @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertThat(studyService).isNotNull();
  }
}