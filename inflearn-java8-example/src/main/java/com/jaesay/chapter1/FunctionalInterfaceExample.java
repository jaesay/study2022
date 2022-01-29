package com.jaesay.chapter1;

import java.util.function.*;

public class FunctionalInterfaceExample {

    public static void main(String[] args) {
        System.out.println("============== 함수형 인터페이스와 람다표현식 소개 ==============");
        // java 8 이전에는?
        // anonymous class 로
        RunSomething runSomething1 = new RunSomething() {
            @Override
            public int doIt(int number) {
                return number + 10;
            }
        };

        // java 8 부터는?
        // functional interface를 instance를 lambda를 이용해서 생성
        RunSomething runSomething2 = number -> number +10; // SAM여서 컴파일러가 추론(infer) 가능
        System.out.println("runSomething2.doIt(1) = " + runSomething2.doIt(1));

        // java에서 function programming
        // 입력값이 같으면 항상 같은 결과를 냄
        // function이 first class citizen + pure function + higher-order function + immutable
        System.out.println("runSomething2.doIt(1) = " + runSomething2.doIt(1));
        System.out.println("runSomething2.doIt(1) = " + runSomething2.doIt(1));

        System.out.println("============== 자바에서 제공하는 함수형 인터페이스 ==============");
        // java에서는 자주 사용하는 functional interface를 미리 제공해준다.
        // 덕분에 굳이 functional interface를 만들지 않고 편하게 사용할 수 있다.
        // 1. Function
        // 한개의 input 입력받고 한개의 output을 return
        Function<Integer, Integer> plus10 = i -> i + 10;
        System.out.println("plus10.apply(1) = " + plus10.apply(1));

        // 조합할 수 있는 default method도 제공
        Function<Integer, Integer> multiply2 = i -> i * 2;
        // *2 => +10
        Function<Integer, Integer> plus10AndMultiply2 = plus10.compose(multiply2);
        System.out.println("plus10AndMultiply2.apply(2) = " + plus10AndMultiply2.apply(2));

        // +10 => *2
        System.out.println("plus10.andThen(multiply2).apply(2) = " + plus10.andThen(multiply2).apply(2));

        // 2. BiFunction
        // 상속받은 functional interface들은 이름을 통해 예측할 수 있다. (e.g. Bi*: 입력값이 두개)
        // 두 개의 값(T, U)를 받아서 R 타입을 리턴하는 functional interface
        // Function을 상속받았기 때문에 default method도 똑같이 사용가능
        BiFunction<Integer, Integer, Integer> sumTwoInputs = (i, j) -> i + j;
        System.out.println("sumTwoInputs.apply(1, 2) = " + sumTwoInputs.apply(1, 2));

        // 3. UnaryOperator
        // Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 functional interface
        UnaryOperator<Integer> plus10_v2 = i -> i + 10;
        System.out.println("plus10_v2.apply(1) = " + plus10_v2.apply(1));

        // 4. BinaryOperator
        // BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입렵값 두개를 받아 리턴하는 functional interface
        BinaryOperator<Integer> sumTwoInputs_v2 = (i, j) -> i + j;
        System.out.println("sumTwoInputs_v2.apply(1, 2) = " + sumTwoInputs_v2.apply(1, 2));

        // 5. Consumer
        // T 타입을 받아서 아무값도 리턴하지 않는 functional interface
        Consumer<Integer> printInt = i -> System.out.println(i);
        printInt.accept(1);

        // 6. Supplier
        // T 타입의 값을 제공하는 functional interface
        Supplier<String> helloSupplier = () -> "hello";
        System.out.println("helloSupplier.get() = " + helloSupplier.get());

        // 7. Predicate
        // T 타입을 받아서 boolean을 functional interface
        Predicate<Integer> isEven = i -> i%2 == 0;
        System.out.println("isEven.test(2) = " + isEven.test(2));

        // local class 나 익명 클래스에서도 적용되는 규칙이었다
        // java8 이전에는 final을 반드시 붙여줘야 했다.
    }
}