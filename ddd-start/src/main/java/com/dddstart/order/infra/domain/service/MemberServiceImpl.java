package com.dddstart.order.infra.domain.service;

import com.dddstart.order.command.domain.Canceller;
import com.dddstart.order.command.domain.Orderer;
import com.dddstart.order.command.domain.MemberService;
import com.dddstart.order.common.DomainService;
import com.dddstart.order.infra.client.MemberClient;
import com.dddstart.order.infra.client.MemberDto;

// TODO 이름 Impl 말고 다른거 없는지?
@DomainService // 외부 시스템의 모델과 현재 도메인 모델간의 변환을 책임
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

    @Override
    public Canceller findCanceller(long memberId) {
        MemberDto member = memberClient.getMember(memberId);
        if (member == null) {
            throw new RuntimeException("member not found");
        }
        return toCanceller(member);
    }

    private Canceller toCanceller(MemberDto member) {
        return new Canceller(member.getMemberId());
    }

    // 복잡할 시 mapper/translator/converter 등 별도 클래스 만들고 변환 처리
    private Orderer toOrderer(MemberDto member) {
        return Orderer.create(member.getMemberId(), member.getName());
    }
}
