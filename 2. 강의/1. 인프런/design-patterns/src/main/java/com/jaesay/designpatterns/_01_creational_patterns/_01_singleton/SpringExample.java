package com.jaesay.designpatterns._01_creational_patterns._01_singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 스프링에서 싱글톤 스콥
 * 등록된 빈을 애플리케이션 컨텍스트 내에서 싱글톤으로 관리
 */
public class SpringExample {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String hello = applicationContext.getBean("hello", String.class);
        String hello2 = applicationContext.getBean("hello", String.class);
        System.out.println(hello == hello2);
    }
}
