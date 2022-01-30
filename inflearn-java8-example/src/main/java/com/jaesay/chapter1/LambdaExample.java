package com.jaesay.chapter1;

import java.util.Arrays;
import java.util.function.*;

public class LambdaExample {
    public static void main(String[] args) {
        System.out.println("============== 람다 표현식 ==============");
        LambdaExample lambdaExample = new LambdaExample();
        lambdaExample.run();

        System.out.println("============== 메소드 레퍼런스 ==============");
        // static method를 사용한 method reference
        UnaryOperator<String> hi = Greeting::hi;
        System.out.println("hi.apply(\"jaeseong\") = " + hi.apply("jaeseong"));

        // 특정 객체의 인스턴스 메소드 참조
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println("hello.apply(\"jaeseong\") = " + hello.apply("jaeseong"));

        // 생성자 참조
        Supplier<Greeting> greetingSupplier = Greeting::new;
        System.out.println("greetingSupplier.get().getClass() = " + greetingSupplier.get().getClass());

        Function<String, Greeting> greetingFunction = Greeting::new;
        System.out.println("greetingFunction.apply(\"hello\").getClass() = " + greetingFunction.apply("hello").getClass());

        // 임의 객체의 인스턴스 메소드 참조
        String[] names = {"김이름", "이이름", "박이름"};
        Arrays.sort(names, String::compareToIgnoreCase); // 배열에 들어있는 임의 객체들이 가지고 있는 메소드 사용
        System.out.println("Arrays.toString(names) = " + Arrays.toString(names));
    }

    private void run() {
        // 컴파일러가 타입 추론(infer)이 가능하여 입력값에 굳이 타입을 붙일 필요 없음
        BinaryOperator<Integer> sum = (i, j) -> i + j;

        // java 8 부터는 로컬 클래스, 익명 클래스, 람다에 의해 참조될 때 effectively final 경우 생략할 수 있다.
        // 로컬 변수 캡쳐 가능
        int baseNumber = 10;

        // local class
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11; // shadowing
                System.out.println(baseNumber);
            }
        }

        // anonymous class
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) { // shadowing
                System.out.println(baseNumber);
            }
        };

        // lambda
        IntConsumer intConsumer = i -> { // lambda는 shadowing 하지 않음
            System.out.println(i + baseNumber);
        };

//        baseNumber++; effectively final이 아니게 되어 로컬 클래스, 익명 클래스, 람다에서  에러 발생
    }
}
