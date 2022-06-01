package com.jaesay.designpatterns._03_behavioral_patterns._21_strategy._02_after;

// Context: Strategy를 사용해서 일을 처리
public class BlueLightRedLight {
    public void blueLight(Speed speed) {
        speed.blueLight();
    }
    public void redLight(Speed speed) {
        speed.redLight();
    }
}
