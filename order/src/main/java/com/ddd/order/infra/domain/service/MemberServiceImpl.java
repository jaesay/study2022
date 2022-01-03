package com.ddd.order.infra.domain.service;

import com.ddd.order.domain.model.Orderer;
import com.ddd.order.domain.service.MemberService;
import com.ddd.order.domain.support.annotation.DomainService;
import com.ddd.order.infra.client.MemberClient;
import com.ddd.order.infra.client.MemberDto;

// TODO 이름 Impl 말고 다른거 없는지?
@DomainService // 도메인 서비스: 외부 시스템의 모델과 현재 도메인 모델간의 변환을 책임
public class MemberServiceImpl implements MemberService {

    private final MemberClient memberClient;

    public MemberServiceImpl(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @Override
    public Orderer findOrderer(long memberId) {
        MemberDto member = memberClient.getMember(memberId);
        if (member == null) {
            throw new RuntimeException("member not found");
        }
        return toOrderer(member);
    }

    // 복잡할 시 mapper/translator 등 별도 클래스 만들고 변환 처리
    private Orderer toOrderer(MemberDto member) {
        return new Orderer(member.getMemberId(), member.getName());
    }
}
