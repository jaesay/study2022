package chapter7.item42;

import java.util.*;

public class Item42Example {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "orange", "pear", "melon");

        // jdk 1.1 - 익명클래스
        words.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        // jdk 1.8 - 람다
        Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        // 생성 메서드
        Collections.sort(words, Comparator.comparingInt(String::length));

        // default method
        words.sort(Comparator.comparingInt(String::length));

        // 로컬변수캡처, effectively final, 쉐도윙 X
        // https://github.com/jaesay/java-study/blob/main/inflearn-java8-example/src/main/java/com/jaesay/chapter1/LambdaExample.java

        // 람다도 익명 클래스처럼 직렬화 형태가 구현별로(가령 가상머신별로) 다를 수가 있다. 따라서 람다를 직렬화하는 일은 극히 삼가해야 한다.
        // 직렬화해야만 하는 함수 객체가 있다면(가령 Comparator처럼) private 정적 중첩 클래스의 인스턴스를 사용하자.
    }
}
