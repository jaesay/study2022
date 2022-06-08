package com.jaesay.designpatterns._03_behavioral_patterns._22_template._02_after.templatecallback;

// 추상메서드가 하나만 있음, 더 필요하다면 추가적인 interface 생성 필요
public class Plus implements Operator {
    @Override
    public int getResult(int result, int number) {
        return result += number;
    }
}
