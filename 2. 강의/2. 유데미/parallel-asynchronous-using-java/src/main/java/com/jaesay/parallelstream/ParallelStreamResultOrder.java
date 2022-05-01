package com.jaesay.parallelstream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jaesay.util.LoggerUtil.log;

public class ParallelStreamResultOrder {

    // parallel stream이 order을 maintain 하는지 확인
    // collection의 타입과 spliterator의 구현체와 일치
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> resultList = toMultipliedIntegerList(integerList);
        Set<Integer> resultSet = toMultipliedIntegerSet(integerList);
        log("resultList = " + resultList); // 순서 보장
        log("resultSet = " + resultSet); // 순서 보장 X
    }

    private static List<Integer> toMultipliedIntegerList(List<Integer> integerList) {
        return integerList.parallelStream().map(integer -> integer * 2).collect(Collectors.toList());
    }

    private static Set<Integer> toMultipliedIntegerSet(List<Integer> integerList) {
        return integerList.parallelStream().map(integer -> integer * 2).collect(Collectors.toSet());
    }
}
