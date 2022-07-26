package com.jaesay.designpatterns._03_behavioral_patterns._22_template._02_after;

import com.jaesay.designpatterns._03_behavioral_patterns._22_template._02_after.templatecallback.Operator;

public class Plus extends FileProcessor {
    public Plus(String path) {
        super(path);
    }

    @Override
    public int getResult(int result, int number) {
        return result += number;
    }
}
