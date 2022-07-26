package org.jaesay.chapter02;

// 협력(collaboration): 시스템의 어떤 기능을 구현하기 위해 객체들 사이에 이뤄지는 상호작용
public class Reservation {
    private Customer customer;
    private Screening screening;
    private Money fee;
    private int audienceCount;

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
