package com.jaesay.designpatterns._01_creational_patterns._01_singleton;

/**
 * 자바에서 사용하고 있는 싱글톤 패턴 예
 */
public class RuntimeExample {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("runtime.maxMemory() = " + runtime.maxMemory());
        System.out.println("runtime.freeMemory() = " + runtime.freeMemory());
    }
}
