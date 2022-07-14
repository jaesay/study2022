package com.jaesay.chapter2;

public class FooImpl implements Foo, Bar {
    private String name;

    public FooImpl(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    // interface 구현체가 default method를 재정의할 수도 있다.
    // 구현한 interface들이 같은 default method를 가지고 있다면 재정의해야 한다. (ddd)
    @Override
    public void printUpperCaseName() {
        System.out.println(this.name.toUpperCase());
    }
}
