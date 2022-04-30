package chapter5.item30;

import java.util.*;
import java.util.function.UnaryOperator;

public class Item30Example {

    public static void main(String[] args) {
        Set<String> nameSet1 = Set.of("김이름", "이이름");
        Set<String> nameSet2 = Set.of("박이름", "최이름");
        Set<String> nameUnionSet = union_v2(nameSet1, nameSet2);

        System.out.println("nameUnionSet = " + nameUnionSet);

        List<Integer> integerList = Arrays.asList(1, 2, 3);
        // 제네릭 싱글턴 팩토리
        // 불변 객체를 여러 타입으로 활용할 수 있게 만들어야 할 때
        // 타입 매개변수에 맞게 매번 그 객체의 타입을 바꿔주는 정적 팩토리
        integerList.sort(Collections.reverseOrder());
        List<Object> objectList = Collections.emptyList();

        String[] nameArray = {"김이름", "이이름", "박이름"};
        UnaryOperator<String> sameString = identityFunction();
        for (String name : nameArray) {
            System.out.println(sameString.apply(name));
        }

        System.out.println(max(integerList));
    }

    public static Set union_v1(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

    // 제네릭 메소드
    // 매개변수화 타입을 받는 정적 유틸리티 메소드는 보통 제네릭이다.
    // 타입 매개변수 목록은 메소드의 제한자와 반환타입 사이에
    public static <E> Set<E> union_v2(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    private static UnaryOperator<Object> IDENTITY_FN = t -> t;

    // 항등함수이기 때문에 T가 어떤 타입든 UnaryOperator<T>를 사용해도 안전하다.
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    // 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정할 수 있다.
    // 재귀적 타입 한정(recusive type bound)이라는 개념이다.
    // 주로 Comparable interface와 함께 쓰인다.
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty()) throw new IllegalArgumentException();

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }
}
