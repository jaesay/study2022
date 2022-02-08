package com.jaesay.parallelstream;

import com.jaesay.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.jaesay.util.CommonUtil.*;
import static com.jaesay.util.LoggerUtil.log;

public class ParallelStreamExample {
    public static void main(String[] args) {

        List<String> namesList = DataSet.namesList();
        ParallelStreamExample parallelStreamExample = new ParallelStreamExample();
        startTimer();
        List<String> resultList = parallelStreamExample.toNameLengthTransform(namesList);
        log("resultList = " + resultList);
        timeTaken();
    }

    private List<String> toNameLengthTransform(List<String> namesList) {
        return namesList
//                .stream()
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }
}
