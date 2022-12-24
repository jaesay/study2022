package com.example.inflearnthejavatest;

import java.util.Optional;

public interface MemberService {
  Optional<Member> findById(Long id);
}
