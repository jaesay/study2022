package com.jaesay.parallelstream;

import java.util.List;

// reduce의 identity의 side effect가 발생할 수 있다.
public class ReduceIdentityExample {

    public int reduce_sum_parallelStream(List<Integer> list) {
        return list.parallelStream()
//                .sequential()
//                .reduce(1, Integer::sum);
                .reduce(0, Integer::sum);
    }

    public int reduce_multiply_parallelStream(List<Integer> list) {
        return list.parallelStream()
                .reduce(1, (x, y) -> x * y);
    }
}
