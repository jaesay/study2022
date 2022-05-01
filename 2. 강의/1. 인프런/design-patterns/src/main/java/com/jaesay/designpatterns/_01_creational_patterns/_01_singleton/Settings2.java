package com.jaesay.designpatterns._01_creational_patterns._01_singleton;

public class Settings2 {
    private static Settings2 instance;

    private Settings2() {
    }

    // 초기화 후에도 메소드에 하나씩 밖에 못들어 오게 된다..
    public synchronized static Settings2 getInstance() {
        if (instance == null) {
            instance = new Settings2();
        }
        return instance;
    }
}
