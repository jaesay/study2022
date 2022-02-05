package chapter5.item26;

import java.util.Set;

public class Item26_7 {
    //  로 타입 써도 되는 예외
    static void convert(Object o) {
        // 1. class 리터럴에 매개변수화 타입을 사용하지 못한다. (배열과 기본타입은 허용) - List.class, String[].class, int.class
        // 2. instance 연산자
        if (o instanceof Set) { // 런타임에는 generic 타입 정보가 지워지므로 raw 타입이나 unbounded wildcard와 동일하게 동작
            Set<?> s = (Set<?>) o; // 와일드 타입으로 형변환
        }
    }
}
