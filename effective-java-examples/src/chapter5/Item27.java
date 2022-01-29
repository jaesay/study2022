package chapter5;

import java.util.ArrayList;
import java.util.List;

public class Item27 {
    // 배열은 covariant
    Super[] supers = new Sub[] {};

    // 제네릭은 invariant
//    List<Super> superList = new ArrayList<Sub>();

    // 자바에서 제네릭이 공변이 될 경우 문제
//    List<Integer> numbers = List.of(1, 2, 3, 4, 5);
//    List<Object> objects = numbers;
//    objects.add("aaa");
}

class Sub extends Super {

}

class Super {

}
