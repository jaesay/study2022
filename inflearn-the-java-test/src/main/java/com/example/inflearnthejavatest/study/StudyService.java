package com.example.inflearnthejavatest.study;

import com.example.inflearnthejavatest.domain.Member;
import com.example.inflearnthejavatest.domain.Study;
import com.example.inflearnthejavatest.member.MemberService;

public class StudyService {
  private final MemberService memberService;
  private final StudyRepository studyRepository;

  public StudyService(MemberService memberService,
      StudyRepository studyRepository) {
    this.memberService = memberService;
    this.studyRepository = studyRepository;
  }

  public Study createNewStudy(Long memberId, Study study) {
    Member member = memberService.findById(memberId).orElseThrow(
        () -> new IllegalArgumentException("Member doesn't exist for id: " + memberId));

    study.setOwnerId(member.getId());
    memberService.notify(study);
    memberService.notify(member);
    return studyRepository.save(study);
  }
}
