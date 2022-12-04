package com.example.keywordalarmappbackend.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

  MemberEntity findByUsername(String username);
  Boolean existsByUsername(String username);
}
