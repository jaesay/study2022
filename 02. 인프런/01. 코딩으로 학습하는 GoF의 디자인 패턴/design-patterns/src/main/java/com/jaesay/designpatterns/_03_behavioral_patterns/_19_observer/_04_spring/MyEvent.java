package com.jaesay.designpatterns._03_behavioral_patterns._19_observer._04_spring;

public class MyEvent {

    private String message;

    public MyEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
