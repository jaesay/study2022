package com.example.discountalarmappbackend.keyword;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
  List<KeywordEntity> findAllByMemberId(long memberId);
}
