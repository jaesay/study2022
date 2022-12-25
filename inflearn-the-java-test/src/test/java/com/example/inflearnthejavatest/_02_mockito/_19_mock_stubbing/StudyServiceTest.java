package com.example.inflearnthejavatest._02_mockito._19_mock_stubbing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.example.inflearnthejavatest.Member;
import com.example.inflearnthejavatest.MemberService;
import com.example.inflearnthejavatest.StudyRepository;
import com.example.inflearnthejavatest.StudyService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

  @Test
  void createStudyService(@Mock MemberService memberService,
      @Mock StudyRepository studyRepository) {

    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    Member member = new Member();
    member.setId(1L);
    member.setEmail("jaesay3153@gmail.com");

    when(memberService.findById(any()))
        .thenReturn(Optional.of(member))
        .thenThrow(new RuntimeException())
        .thenReturn(Optional.empty());

    assertEquals("jaesay3153@gmail.com", memberService.findById(1L).get().getEmail());

    assertThrows(RuntimeException.class, () -> {
      memberService.findById(2L);
    });

    assertEquals(Optional.empty(), memberService.findById(3L));

    doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

    assertThrows(IllegalArgumentException.class, () -> {
      memberService.validate(1L);
    });

    memberService.validate(2L);
  }
}