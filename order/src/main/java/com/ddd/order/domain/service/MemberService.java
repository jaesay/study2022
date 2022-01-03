package com.ddd.order.domain.service;

import com.ddd.order.domain.model.Orderer;

public interface MemberService {
    Orderer findOrderer(long memberId);
}
