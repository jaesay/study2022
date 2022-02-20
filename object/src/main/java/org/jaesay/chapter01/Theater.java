package org.jaesay.chapter01;

public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    // ticket sellect의 내부 맴버 필드(ticker office)/구현 영역에 접근하지 않고 인터페이스에만 의존
    // 객체를 인터페이스와 구현으로 나누고 인터페이스만 공개하는 것은 객체 사이의 결합도를 낮추고 변경하기 쉬운 코드를 작성하기 위해 따라야 하는 가장 기본적인 설계 원칙
    public void enter(Audience audience) {
        ticketSeller.sellTo(audience);
    }
}
