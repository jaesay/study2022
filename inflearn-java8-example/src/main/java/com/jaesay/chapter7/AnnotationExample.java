package com.jaesay.chapter7;

import java.util.Arrays;

@Chicken("양념")
@Chicken("마늘간장")
public class AnnotationExample {
    public static void main(String[] args) {
        ChickenContainer chickenContainer = AnnotationExample.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });
    }
}
