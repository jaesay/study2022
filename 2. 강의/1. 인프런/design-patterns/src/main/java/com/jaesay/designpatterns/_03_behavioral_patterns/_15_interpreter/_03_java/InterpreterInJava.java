package com.jaesay.designpatterns._03_behavioral_patterns._15_interpreter._03_java;

import java.util.regex.Pattern;

public class InterpreterInJava {

    public static void main(String[] args) {
        System.out.println(Pattern.matches(".pr...", "spring"));
    }
}
