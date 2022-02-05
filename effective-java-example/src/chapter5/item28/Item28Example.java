package chapter5.item28;

import java.util.ArrayList;
import java.util.List;

public class Item28Example {

    public static void main(String[] args) {
        // 배열은 covariant
        Super[] supers = new Sub[] {};

        // 제네릭은 invariant
//    List<Super> superList = new ArrayList<Sub>();

        // 자바에서 제네릭이 공변이 될 경우 문제
        // 런타임에 실패한다.
//        Object[] objectArray = new Long[1];
//        objectArray[0] = "타입이 달라 넣을 수 없다."; // ArrayStoreException을 던진다.

        // 컴파일되지 않는다.
//    List<Object> objectList = new ArrayList<Long>(); // 호환되지 않는 타입이다.

        List<Integer> integerList = List.of(1, 2, 3);
        Chooser<Integer> chooser = new Chooser<>(integerList);
        System.out.println("chooser.choose() = " + chooser.choose());
    }


    static class Sub extends Super {

    }

    static class Super {

    }
}
