package com.jaesay.chapter2;

public interface SubFoo extends Foo {
    // default method를 다시 abstract method로 제공할 수 있다.
    void printUpperCaseName();
}
