package com.jaesay.designpatterns._01_creational_patterns._01_singleton;

public enum Settings5 {
    // 리플랙션, 직렬화 모두 방어 가능
    // 단점: lazy initialization 과 Enum끼리는 확장 불가 (이팩티브자바 아이템 38 참고)
    INSTANCE
}
