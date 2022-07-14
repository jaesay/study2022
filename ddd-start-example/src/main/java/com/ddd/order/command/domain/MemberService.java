package com.ddd.order.command.domain;

public interface MemberService {
    Orderer findOrderer(long memberId);
    Canceller findCanceller(long memberId);
}
