package com.jaesay.designpatterns._01_creational_patterns._01_singleton;

/**
 * double-checked locking
 */
public class Settings3 {
    private static volatile Settings3 instance;

    public Settings3() {
    }

    // 초기화된 후부터는 lock이 걸리지 않게 된다.
    // 로컬변수를 추가하여 volatile 맴버변수로 초기화하면 메인메모리에서 안가져오기 떄문에 더 빠르게 만들 수 있다.
    public static Settings3 getInstance() {
        if (instance == null) {
            synchronized (Settings3.class) {
                if (instance == null) {
                    instance = new Settings3();
                }
            }
        }
        return instance;
    }
}
