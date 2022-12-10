package com.example.keywordalarmappbackend.keyword;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<UserKeywordEntity, Long> {
  List<UserKeywordEntity> findAllByMemberId(long memberId);
}
