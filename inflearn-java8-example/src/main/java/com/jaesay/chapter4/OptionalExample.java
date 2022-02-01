package com.jaesay.chapter4;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class OptionalExample {

    public static void main(String[] args) {
        System.out.println("============== Optional 소개 ==============");
        OnlineClass spring_cloud = new OnlineClass(6, "spring cloud", false);
        // java 8 이전에는?
        // 클라이언트의 실수를 유발할 수 있음
        if (spring_cloud.getProgress() != null) {
            // ...
        }
        // java 8 이후는?
        // Optional은 return 값으로만 쓸 것을 권장
        spring_cloud.getProgress().ifPresent(System.out::println);


        System.out.println("============== Optional API ==============");
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).findFirst();

        System.out.println("Optional에 값이 있는지 없는지 확인하기");
        if (optional.isPresent()) {}
        if (optional.isEmpty()) {} // java 11 부터

        // 있을 경우 다른 action을 하는 api를 사용
        optional.ifPresent(System.out::println);
        optional.ifPresentOrElse(System.out::println, () -> System.out.println("No Element"));

        OptionalInt optionalInt = OptionalInt.of(10);

        System.out.println("Optional에 있는 값 가져오기");
        // optional에서 꺼낼 때는 get 보단 다른 api를 활용
//        optional.get();

        System.out.println("Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라.");
        // 상수 등 미리 만들어져 있는 값으로 초기화할 때
        optional.orElse(createNewClass());

        // 동적으로 (lazy)
        optional.orElseGet(OptionalExample::createNewClass);

        System.out.println("Optional에 값이 있으면 가져오고 없는 경우 에러를 던져라.");
        optional.orElseThrow();
        optional.orElseThrow(IllegalStateException::new);

        System.out.println("Optional에 들어있는 값 걸러내기");
        Optional<OnlineClass> onlineClass = optional.filter(oc -> oc.getId() > 10);

        System.out.println("Optional에 들어있는 값 변환하기");
        Optional<String> optionalTitle = optional.map(OnlineClass::getTitle);
        // Optional<Optional<T>> 일때 한번 벗겨냄
        Optional<Progress> progress = optional.flatMap(OnlineClass::getProgress);
    }

    private static OnlineClass createNewClass() {
        System.out.println("create new class");
        return new OnlineClass(6, "spring data jpa", true);
    }
}
