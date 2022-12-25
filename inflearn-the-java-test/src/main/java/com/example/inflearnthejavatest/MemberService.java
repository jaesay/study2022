package com.example.inflearnthejavatest;

import java.util.Optional;

public interface MemberService {
  Optional<Member> findById(Long memberId);

  void validate(Long memberId);

  void notify(Study study);

  void notify(Member member);
}
