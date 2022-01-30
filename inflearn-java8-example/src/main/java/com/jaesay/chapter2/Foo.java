package com.jaesay.chapter2;

public interface Foo {

    void printName();

    String getName();

    // java 8 부터는 default method 가능
    // 기존 구현체들이 추가로 재정의할 필요 없이 사용 가능하지만 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
    // 따라서 반드시 문서화 필요
    /**
    * @implSpec 이 구현체는 대문자로 출력한다.
    * */
    default void printUpperCaseName() {
        System.out.println(getName().toUpperCase());
    }

    // static method로 해당 타입 관련 helper or util method를 제공할 수 있다.
    static void printAnything() {
        System.out.println("Foo");
    }

    // Object가 제공하는 기능은 기본메소드로 제공할 수 없다.
//    default String toString() {
//        return "toString";
//    }
}
