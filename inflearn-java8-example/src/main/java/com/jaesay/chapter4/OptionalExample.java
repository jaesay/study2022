package com.jaesay.chapter4;


import java.util.ArrayList;
import java.util.List;

public class OptionalExample {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_cloud = new OnlineClass(6, "spring cloud", false);
        // java 8 이전에는?
        // 클라이언트의 실수를 유발할 수 있음
        if (spring_cloud.getProgress() != null) {
            // ...
        }
        // java 8 이후는?
        // Optional은 return 값으로만 쓸 것을 권장
        spring_cloud.getProgress().ifPresent(System.out::println);
    }
}
