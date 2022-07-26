package com.jaesay.parallelstream;

import com.jaesay.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelStreamPerformanceTest {

    ParallelStreamPerformance intStreamExample = new ParallelStreamPerformance();

    @Test
    void sum_using_intstream() {
        //given
        int count = 1_000_000;
        boolean isParallel = false;

        //when
        int sum = intStreamExample.sum_using_intstream(count, isParallel);
        System.out.println("sum : " + sum);

        //then
        assertEquals(1_784_293_664, sum);
    }

    @Test
    void sum_using_intstream_parallel() {
        //given
        int count = 1_000_000;
        boolean isParallel = true;

        //when
        int sum = intStreamExample.sum_using_intstream(count, isParallel);
        System.out.println("sum : " + sum);

        //then
        assertEquals(1_784_293_664, sum);
    }

    @Test
    void sum_using_iterate() {
        //given
        int n = 1_000_000;
        boolean isParallel = false;

        //when
        int sum = intStreamExample.sum_using_iterate(n, isParallel);
        System.out.println("sum : " + sum);

        //then
        assertEquals(1_784_293_664, sum);
    }

    @Test
    void sum_using_iterate_parallel() {
        //given
        int n = 1_000_000;
        boolean isParallel = true;

        //when
        int sum = intStreamExample.sum_using_iterate(n, isParallel);
        System.out.println("sum : " + sum);

        //then
        assertEquals(1_784_293_664, sum);
    }

    @Test
    void sum_using_list() {
        //given
        List<Integer> inputList = DataSet.generateArrayList(1_000_000);

        //when
        int sum = intStreamExample.sum_using_list(inputList, false);
        System.out.println("sum : " + sum);

        //then
        assertEquals(1_784_293_664, sum);
    }

    @Test
    void sum_using_list_parallel() {
        //given
        List<Integer> inputList = DataSet.generateArrayList(1_000_000);

        //when
        int sum = intStreamExample.sum_using_list(inputList, true);
        System.out.println("sum : " + sum);

        //then
        assertEquals(1_784_293_664, sum);
    }
}