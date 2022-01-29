package com.jaesay.chapter1;

public class FunctionalInterfaceExample {

    public static void main(String[] args) {
        // java 8 이전에는?
        // 익명클래스로
        RunSomething runSomething1 = new RunSomething() {
            @Override
            public int doIt(int number) {
                return number + 10;
            }
        };

        // java 8 부터는?
        // functional interface를 instance를 lambda를 이용해서 생성
        RunSomething runSomething2 = number -> number +10;
        System.out.println(runSomething2.doIt(1));

        // java에서 function programming
        // 입력값이 같으면 항상 같은 결과를 냄
        // function이 first class citizen + pure function + higher-order function + immutable
        System.out.println(runSomething2.doIt(1));
        System.out.println(runSomething2.doIt(1));
    }
}