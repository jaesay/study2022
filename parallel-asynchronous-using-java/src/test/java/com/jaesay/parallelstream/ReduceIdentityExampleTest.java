package com.jaesay.parallelstream;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReduceIdentityExampleTest {

    ReduceIdentityExample reduceIdentityExample = new ReduceIdentityExample();

    @Test
    void reduce_sum_parallelStream() {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        int result = reduceIdentityExample.reduce_sum_parallelStream(integerList); // identity를 1로 바꿀경우 44 -> 첫번째 parallel 계산 시 1 + x
        assertEquals(36, result);
    }

    @Test
    void reduce_multiply_parallelStream() {
        List<Integer> integerList = List.of(1, 2, 3, 4);
        int result = reduceIdentityExample.reduce_multiply_parallelStream(integerList);
        assertEquals(24, result);
    }
}