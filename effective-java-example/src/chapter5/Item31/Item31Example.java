package chapter5.Item31;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

public class Item31Example {
    public static void main(String[] args) {
        Stack<Number> numberStack = new Stack<>();
        List<Integer> intList = List.of(1, 2, 3);
        // 제네릭은 불공변이기 떄문에 에러 발생
//        numberStack.pushAll_v1(intList);

        // 해결책은?
        // 한정적 와일드카드 타입
        numberStack.pushAll_v2(intList);

        List<Object> objects = new ArrayList<>();
//        numberStack.popAll_v1(objects);
        numberStack.popAll_v2(objects);
        System.out.println(objects);

        Chooser<Number> chooser = new Chooser<>(intList);
        System.out.println("chooser.choose() = " + chooser.choose());

        // 자바 8 부터는 타입 추론(type inference) 능력 향상
        // The target type of an expression is the data type that the Java compiler expects depending on where the expression appears
        Set<Integer> integerSet = Set.of(1, 3, 5);
        Set<Double> doubleSet = Set.of(2.0, 4.0, 6.0);
        Set<Number> numberSet = union(integerSet, doubleSet);
        System.out.println("numberSet = " + numberSet);

        // 자바 8 이전 명시적 타입 인수(explicit type argument)
        Set<Number> numberSet2 = Item31Example.<Number>union(integerSet, doubleSet);

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4);
        swap2(integerList, 1, 2);
    }

    // 반환 타입에는 한정적 와일드카드 타입을 사용하면 안 된다. 유연성을 높여주기는커녕 클라이언트 코드에서도 와일드카드 타입을 써야 하기 때문이다.
    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    // Comparable(or Comparator)을 직접 구현하지 않고, 직접 구현한 다른 타입을 확장한 타입을 지원하기 위해 와일드카드가 필요
    // E를 상위클래스가 Comparable을 구현했다면
    public static <E extends Comparable<? super E>> E max(List<? extends E> c) {
        if (c.isEmpty()) throw new IllegalArgumentException();

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    // 메소드 선언에 타입 매개변수가 한 번만 나오면 와일드카드로 대체하라
    // public API라면 간단한 두 번째가 낫다. 어떤 리스트든 이 메소드에 넘기면 명시한 인덱스의 원소들을 교환해줄 것이다. 신경써야할 타입 매개변수도 없다.
    public static <E> void swap1(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
    public static void swap2(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    public static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
