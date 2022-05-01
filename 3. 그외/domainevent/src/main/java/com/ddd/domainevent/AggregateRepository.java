package com.ddd.domainevent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregateRepository extends JpaRepository<Aggregate, Long> {
}
