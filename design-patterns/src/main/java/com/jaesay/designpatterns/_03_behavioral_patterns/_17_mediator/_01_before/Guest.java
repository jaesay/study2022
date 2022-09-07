package com.jaesay.designpatterns._03_behavioral_patterns._17_mediator._01_before;

// 각각의 컴포넌트들이 엮여있음
public class Guest {

    private Restaurant restaurant = new Restaurant();

    private CleaningService cleaningService = new CleaningService();

    public void dinner() {
        restaurant.dinner(this);
    }

    public void getTower(int numberOfTower) {
        cleaningService.getTower(this, numberOfTower);
    }

}
