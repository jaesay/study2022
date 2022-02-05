package chapter5.item26;

import java.util.ArrayList;
import java.util.List;

public class Item26_4 {

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
}
