package com.jaesay.parallelstream;

import com.jaesay.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpliteratorExampleTest {

    ArrayListSpliteratorExample arrayListSpliteratorExample = new ArrayListSpliteratorExample();

    // JVM warm up & CPU data와 instructions를 캐시
    // maximum performance을 확인할 수 있음
    // ArrayList 특성상 많이 차이는 나지 않음(indexed collection)
    @RepeatedTest(5)
    void multiplyEachValue() {
        // given
        int size = 1_000_000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        // when
        List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(inputList, 2, false);

        // then
        assertEquals(size, resultList.size());
    }

    @RepeatedTest(5)
    void multiplyEachValue_parallel() {
        // given
        int size = 1_000_000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);

        // when
        List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(inputList, 2, true);

        // then
        assertEquals(size, resultList.size());
    }
}