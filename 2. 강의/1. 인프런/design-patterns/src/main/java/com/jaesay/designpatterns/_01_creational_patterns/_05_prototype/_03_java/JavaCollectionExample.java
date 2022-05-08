package com.jaesay.designpatterns._01_creational_patterns._05_prototype._03_java;

import java.util.ArrayList;
import java.util.List;

public class JavaCollectionExample {

    public static void main(String[] args) {
        List<Student> students = List.of(new Student("김이름"), new Student("박이름"));

        List<Student> clone = new ArrayList<>(students); // list는 clonable을 상속받지 않음
        System.out.println(clone);
    }
}
