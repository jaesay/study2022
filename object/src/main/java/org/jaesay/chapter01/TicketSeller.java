package org.jaesay.chapter01;

public class TicketSeller {
    // 캠슐화 => 세부사항을 감춤 => low coupling => 변경하기 쉬운 객체
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    // ticket seller는 ticket office 에서 티켓을 꺼내거나 판매 요금을 적립하는 일을 스스로 수행할 수 밖에 없다
    // ticket seller는 audience 인터페이스에만 의존
    // ticket office에 자율성보다는 audience에 결합도를 낮추는 것이 더 중요하다고 판단
    public void sellTo(Audience audience) { // 훌륭한 객체지향 설계란 협력하는 객체 사이의 의존성을 적적하게 관리하는 설계이다. 객체지향간의 상호작용을 하기위해서는 의존성이 필요하다.
        ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));
    }
}
