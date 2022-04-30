package chapter5.item26;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Item26Example {
    // parameterized type: raw vs Object
    // raw 타입을 쓰면 제네릭이 안겨주는 안전성과 표현력을 모두 잃게 된다.
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, 42);
//        safeAdd(list, 42); // 컴파일 에러 발생
        String s = strings.get(0); // 런타임 때 ClassCastException 발생
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o); // raw 타입 경고
    }

    private static void safeAdd(List<Object> list, Object o) {
        list.add(o);
    }

    // 잘못된 예 - 모르는 타입의 원소도 받는 raw 타입을 사용했다.
    static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result; // 공통원소 수
    }

    // unbounded wildcard type 사용 - 타입 안전하며 유연하다.
    static int numElementsInCommon1(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result; // 공통원소 수
    }

    //  로 타입 써도 되는 예외
    static void convert(Object o) {
        // 1. class 리터럴에 매개변수화 타입을 사용하지 못한다. (배열과 기본타입은 허용) - List.class, String[].class, int.class
        // 2. instance 연산자
        if (o instanceof Set) { // 런타임에는 generic 타입 정보가 지워지므로 raw 타입이나 unbounded wildcard와 동일하게 동작
            Set<?> s = (Set<?>) o; // 와일드 타입으로 형변환
        }
    }
}
