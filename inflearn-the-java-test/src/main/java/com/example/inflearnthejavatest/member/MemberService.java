package com.example.inflearnthejavatest.member;

import com.example.inflearnthejavatest.domain.Member;
import com.example.inflearnthejavatest.domain.Study;
import java.util.Optional;

public interface MemberService {
  Optional<Member> findById(Long memberId);

  void validate(Long memberId);

  void notify(Study study);

  void notify(Member member);
}
