package chapter7.item43;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item43Example {

    public static void main(String[] args) {
        List<String> words = List.of("apple", "banana", "orange", "pear", "melon");

        Map<Integer, Integer> map = new HashMap<>();
        // 보통 더 짧고 간결
        // 메서드 참조에는 기능을 잘 드러내는 이름을 지어줄 수 있고 친절한 설명을 문서로 남길 수도 있다.
        map.merge(1, 1, Integer::sum);
        map.merge(2, 1, Integer::sum);
        map.merge(1, 3, Integer::sum);
        System.out.println(map);
    }
}
