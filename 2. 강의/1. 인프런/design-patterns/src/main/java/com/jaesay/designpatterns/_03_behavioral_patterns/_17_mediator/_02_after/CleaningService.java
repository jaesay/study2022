package com.jaesay.designpatterns._03_behavioral_patterns._17_mediator._02_after;

public class CleaningService {

    private FrontDesk frontDesk = new FrontDesk();

    // 게스트를 직접가지지 않고 최소한의 정보만
    public void getTowers(Integer guestId, int numberOfTowers) {
        String roomNumber = this.frontDesk.getRoomNumberFor(guestId);
        System.out.println("provide " + numberOfTowers + " to " + roomNumber);
    }
}
