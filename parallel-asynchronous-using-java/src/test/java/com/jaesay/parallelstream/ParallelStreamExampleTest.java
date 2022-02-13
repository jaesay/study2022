package com.jaesay.parallelstream;

import com.jaesay.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.jaesay.util.CommonUtil.startTimer;
import static com.jaesay.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParallelStreamExampleTest {

    ParallelStreamExample parallelStreamExample = new ParallelStreamExample();

    @Test
    void toNameLengthTransform() {
        // given
        List<String> inputList = DataSet.namesList();

        // when
        startTimer();
        List<String> resultList = parallelStreamExample.toNameLengthTransform(inputList);
        timeTaken();

        // then
        assertEquals(4, resultList.size());
        resultList.forEach(name -> assertTrue(name.contains("-")));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void toNameLengthTransform_v2(boolean isParallel) {
        // given
        List<String> inputList = DataSet.namesList();

        // when
        startTimer();
        List<String> resultList = parallelStreamExample.toNameLengthTransform_v2(inputList, isParallel);
        timeTaken();

        // then
        assertEquals(4, resultList.size());
        resultList.forEach(name -> assertTrue(name.contains("-")));
    }
}