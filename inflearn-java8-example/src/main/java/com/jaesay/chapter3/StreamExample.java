package com.jaesay.chapter3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        System.out.println("============== Stream 소개 ==============");
        List<String> names = new ArrayList<>();
        names.add("김이름");
        names.add("이이름");
        names.add("박이름");
        names.add("최이름");

        // Functional in nature
        // lazy (1. the terminal operation is initiated, 2. source elements are consumed only as needed)
        Stream<String> stringStream = names.stream().map(s -> {
            String s1 = s.toUpperCase();
            System.out.println(s1); // 출력되지 않는다.
            return s1;
        });

        names.forEach(System.out::println);

        // terminal operation 초기화될 시 처리
        List<String> collect = names.stream().map(String::toUpperCase).collect(Collectors.toList());

        // 내부적으로 spliterate() 사용하여 병렬처리 가능
        // parallel이나 멀티쓰레드가 반드시 더 좋지는 않다. (데이터를 쪼개고 합치고, context switch 비용) 일반적으로 stream을 사용하는 것이 좋다. 데이터 소스나 처리에 따라 달라지기 때문에 직접 성능테스트를 해봐야 한다.
        System.out.println("====================================");
        List<String> collect2 = names.parallelStream().map(s -> {
            System.out.println(s + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

        System.out.println("============== Stream API ==============");
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).forEach(System.out::println);

        System.out.println("close되지 않은 수업");
        springClasses.stream().filter(Predicate.not(OnlineClass::isClosed)).forEach(System.out::println);

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream().map(OnlineClass::getTitle).forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(1, "The Java, Test", true));
        javaClasses.add(new OnlineClass(2, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(3, "The Java, 8 to 11", false));

        List<List<OnlineClass>> inflearnClasses = new ArrayList<>();
        inflearnClasses.add(springClasses);
        inflearnClasses.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        inflearnClasses.stream().flatMap(Collection::stream).forEach(oc -> System.out.println(oc.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개까지만");
        Stream.iterate(10, i -> i +1).skip(10).limit(10).forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        System.out.println(javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test")));

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        springClasses.stream().filter(oc -> oc.getTitle().contains("spring")).map(OnlineClass::getTitle).collect(Collectors.toList()).forEach(System.out::println);
    }
}
