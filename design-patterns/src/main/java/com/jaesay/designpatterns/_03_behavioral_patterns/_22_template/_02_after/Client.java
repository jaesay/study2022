package com.jaesay.designpatterns._03_behavioral_patterns._22_template._02_after;

public class Client {

    public static void main(String[] args) {
        FileProcessor fileProcessor = new Plus("number.txt");
        int result = fileProcessor.process();
        System.out.println(result);
    }
}
