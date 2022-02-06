package chapter5.item32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Item32Example {

    public static void main(String[] args) {
        List<String> nameList1 = List.of("김이름", "이이름");
        List<String> nameList2 = List.of("박이름");

        // 제네릭 배열을 프로그래머가 직접 생성하는 건 허용하지 않으면서 제네릭 varargs 매개변수를 받는 메소드를 선언할 수 있게 한 이유는?
        // 오류가 아닌 'Possible heap pollution from parameterized vararg type' 경고만 발생한다.
        // 제네릭이나 매개변수화 타입의 varargs 매개변수를 받는 메서드가 실무에서 매우 유용하기 떄문에 언어 설계자는 이 모순을 수용
        // Arrays.asList(T... a), Collections.addAll(Collection<? super T> c, T... elements), EnumSet.of(E first, E... rest), List.of ...
        // java 7 부터는 @SafeVarars 어노테이션을 통해 메서드 작성자가 경고를 숨길 수 있음
//        dangerous(nameList1, nameList2);

        // Object[] => String[]으로 변환할 때 ClassCastException 발생
//        String[] attributes = pickTwo("좋은", "빠른", "저렴한");
        pickTwo_v2("좋은", "빠른", "저렴한").forEach(System.out::println);
        flatten(List.of(nameList1, nameList2)).forEach(System.out::println);
    }

    static void dangerous(List<String>... stringLists) {
        List<Integer> integerList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = integerList; // 힙오염 발생
        String s = stringLists[0].get(0); // ClassCastException
    }


    // 자신의 제네릭 매개변수 배열의 참조를 노출한다. - 안전하지 않다.
    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0: return toArray(a, b); // Object[]을 만듦
            case 1: return toArray(a, c);
            case 2: return toArray(b, c);
        }
        throw new AssertionError();
    }

    // 다음 두 조건을 모두 만족하는 제네릭 varars 메서드는 안전하다.
    // 1. varargs 매개변수 배열에 아무것도 저장하지 않는다.
    // 2. 그 배열(혹은 복제본)을 신뢰할 수 없는 코드에 노출하지 않는다.
    // @SafeVarargs는 재정의할 수 없는 메서드에만 달아야 한다.
    // java 8 부터는 정적 메서드와 final 인스턴스 메서드에만 붙일 수 있고 java 9 는 private 인스턴스 메서드에도 허용
    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    // varargs 매개변수를 List 매개변수로 바꿔도 됨
    // @SafeVarargs 달 필요 없고, 실수로 안전하다고 판단할 걱정도 없다.
    // 단점으로는 클라이언트 코드가 살짝 지저분해지고 속도가 조금 느려질 수 있다.
    static <T> List<T> flatten(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    static <T> List<T> pickTwo_v2(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0: return List.of(a, b);
            case 1: return List.of(a, c);
            case 2: return List.of(b, c);
        }
        throw new AssertionError();
    }
}
