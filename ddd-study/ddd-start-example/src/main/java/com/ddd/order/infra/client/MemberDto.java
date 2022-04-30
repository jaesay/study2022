package com.ddd.order.infra.client;

public class MemberDto {
    private long memberId;
    private String name;

    public MemberDto(long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }
}
