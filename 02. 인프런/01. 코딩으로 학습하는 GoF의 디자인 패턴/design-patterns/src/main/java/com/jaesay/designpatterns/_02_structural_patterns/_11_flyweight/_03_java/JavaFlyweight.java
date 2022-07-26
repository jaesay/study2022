package com.jaesay.designpatterns._02_structural_patterns._11_flyweight._03_java;

public class JavaFlyweight {
    public static void main(String[] args) {
        Integer i1 = Integer.valueOf(10);
        Integer i2 = Integer.valueOf(10);
        System.out.println(i1 == i2); // 캐싱
    }
}
