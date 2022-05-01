package com.jaesay.designpatterns._01_creational_patterns._02_factory_method._03_java;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 에서의 팩토리 메소드 패턴 예
 */
public class SpringBeanFactoryExample {

    public static void main(String[] args) {
        BeanFactory xmlFactory = new ClassPathXmlApplicationContext("config.xml"); // creator
        String hello = xmlFactory.getBean("hello", String.class); // product
        System.out.println(hello);

        BeanFactory javaFactory = new AnnotationConfigApplicationContext(Config.class);
        String hello1 = javaFactory.getBean("hello", String.class);
        System.out.println(hello1);
    }
}
