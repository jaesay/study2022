package com.jaesay.chapter1;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LambdaExample {
    public static void main(String[] args) {
        LambdaExample lambdaExample = new LambdaExample();
        lambdaExample.run();
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
