package com.ddd.order.infra.client;

public class MemberDto {
    private Long memberId;
    private String name;
    private MemberGrade grade;

    public MemberDto(Long memberId, String name, MemberGrade grade) {
        this.memberId = memberId;
        this.name = name;
        this.grade = grade;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public MemberGrade getGrade() {
        return grade;
    }

    public enum MemberGrade {
        GOLD, SILVER, BRONZE
    }
}
