package chapter5.item26;

import java.util.Set;

public class Item26_5_6 {
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
}
