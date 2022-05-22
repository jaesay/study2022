package com.jaesay.designpatterns._03_behavioral_patterns._17_mediator._02_after;

import java.time.LocalDateTime;

// mediator 자체의 복잡도와 결합도는 증가하지만 컴포넌트들은 확장/재사용/테스트코드 작성이 좋아진다.
public class FrontDesk {

    private CleaningService cleaningService = new CleaningService();

    private Restaurant restaurant = new Restaurant();

    public void getTowers(Guest guest, int numberOfTowers) {
        cleaningService.getTowers(guest.getId(), numberOfTowers);
    }

    public String getRoomNumberFor(Integer guestId) {
        return "1111";
    }

    public void dinner(Guest guest, LocalDateTime dateTime) {
        restaurant.dinner(guest.getId(), dateTime);
    }
}
