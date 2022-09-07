package org.jaesay.chapter01;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    // audience가 bag을 직접처리하기 떄문에 외부에서는 audience가 bag을 가지고 있다는 것을 알 필요 없다.
    public Long buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}
