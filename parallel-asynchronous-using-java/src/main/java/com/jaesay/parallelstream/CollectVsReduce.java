package com.jaesay.parallelstream;

import com.jaesay.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.jaesay.util.LoggerUtil.log;

public class CollectVsReduce {

    public static void main(String[] args) {
        log("collect() = " + collect());
        log("reduce() = " + reduce());
    }

    // 더 효율적이다
    public static String collect() {
        List<String> list = DataSet.namesList();
        return list.parallelStream()
                .collect(Collectors.joining()); // combine phase에서 string builder(mutable)를 사용하여 single string
    }

    public static String reduce() {
        List<String> list = DataSet.namesList();
        return list.parallelStream()
                .reduce("", (x, y) -> x + y); // 계산 시 여러개의 string(immutable)을 생산
    }
}
