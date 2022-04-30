package com.jaesay.chapter2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class InterfaceExample {

    public static void main(String[] args) {
        System.out.println("============== 인터페이스 기본 메소드와 스태틱 메소드 ==============");
        Foo defaultFoo = new FooImpl("jaeseong");
        defaultFoo.printName();
        System.out.println("defaultFoo.getName() = " + defaultFoo.getName());
        defaultFoo.printUpperCaseName();
        Foo.printAnything();

        System.out.println("============== 자바 8 API의 기본 메소드와 스태틱 메소드 ==============");
        List<String> names = new ArrayList<>();
        names.add("김이름");
        names.add("이이름");
        names.add("박이름");
        names.add("최이름");

        // Iterator에 추가된 default method
        // 1. foreach
        names.forEach(System.out::println);

        // 2. spliterator()
        // 소스의 원소를 traverse 하고 partition 하기 위한 interface
        // Stream에서 사용
        Spliterator<String> spliterator = names.spliterator();
        spliterator.tryAdvance(System.out::println);
        spliterator.tryAdvance(System.out::println);
        spliterator.tryAdvance(System.out::println);
        spliterator.tryAdvance(System.out::println);
        spliterator.tryAdvance(System.out::println);

        // Collection의 default method
        // 1. stream()
        List<String> namesStartedWithKim = names.stream().filter(s -> s.startsWith("김")).collect(Collectors.toList());
        namesStartedWithKim.forEach(System.out::println);

        // 2. removeIf()
        names.removeIf(s -> s.startsWith("최"));
        names.forEach(System.out::println);

        // 3. spliterator()
        Spliterator<String> spliterator1 = names.stream().spliterator();

        // Comparator의 default method & static method
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed().thenComparing(Comparator.naturalOrder()));
        names.sort(Comparator.reverseOrder());
    }
}
