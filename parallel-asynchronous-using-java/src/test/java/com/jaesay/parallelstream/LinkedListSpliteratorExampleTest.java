package com.jaesay.parallelstream;

import com.jaesay.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListSpliteratorExampleTest {

    LinkedListSpliteratorExample linkedListSpliteratorExample = new LinkedListSpliteratorExample();

    @RepeatedTest(5)
    void multiplyEachValue() {
        // given
        int size = 1_000_000;
        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);

        // when
        List<Integer> resultList = linkedListSpliteratorExample.multiplyEachValue(inputList, 2, false);

        // then
        assertEquals(size, resultList.size());
    }

    // Linked list의 경우 Splitting , Executing and Combining 오래 걸림
    @RepeatedTest(5)
    void multiplyEachValue_parallel() {
        // given
        int size = 1_000_000;
        LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);

        // when
        List<Integer> resultList = linkedListSpliteratorExample.multiplyEachValue(inputList, 2, true);

        // then
        assertEquals(size, resultList.size());
    }
}