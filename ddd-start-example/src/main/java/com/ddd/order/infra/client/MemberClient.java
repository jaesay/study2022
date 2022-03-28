package com.ddd.order.infra.client;

import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;

import javax.annotation.PostConstruct;

@Component
public class MemberClient {
    ConcurrentReferenceHashMap<Long, MemberDto> memberMap = new ConcurrentReferenceHashMap<>();

    @PostConstruct
    public void init() {
        memberMap.put(1L, new MemberDto(1L, "김이름"));
        memberMap.put(2L, new MemberDto(2L, "박이름"));
    }

    public MemberDto getMember(Long memberId) {
        return memberMap.get(memberId);
    }
}
