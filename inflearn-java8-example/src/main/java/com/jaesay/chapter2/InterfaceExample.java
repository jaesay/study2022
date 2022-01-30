package com.jaesay.chapter2;

public class InterfaceExample {

    public static void main(String[] args) {
        System.out.println("============== 인터페이스 기본 메소드와 스태틱 메소드 ==============");
        Foo defaultFoo = new FooImpl("jaeseong");
        defaultFoo.printName();
        System.out.println("defaultFoo.getName() = " + defaultFoo.getName());
        defaultFoo.printUpperCaseName();
        Foo.printAnything();
    }
}
