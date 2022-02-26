package org.jaesay.chapter04;

// 데이터 중심의 설계
// 객체의 행동보다는 상태에 초점을 맞춘다.
// 객체를 고립시킨 채 오퍼레이션을 정의하도록 만든다.
// => 객체를 그저 단순한 데이터 집합체로 만들 수 있다.
// => 협력이라는 문맥을 벗어나 고립된 상태에 초점을 맞추기 때문에 캠슐화를 위반하기 쉽다.
public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }
}
