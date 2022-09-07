package com.jaesay.designpatterns._03_behavioral_patterns._22_template._02_after.templatecallback;

public class Client {

    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor("number.txt");
        // 새로운 클래스를 정의하지 않을 수 있음..
        // 전략패턴과 다른 점은 인터페이스가 한개의 메서드만 갖는다
        // gof가 정의한 디자인 패턴은 아님..
        int process = fileProcessor.process((result, number) -> result += number);
        System.out.println("process = " + process);
    }
}
