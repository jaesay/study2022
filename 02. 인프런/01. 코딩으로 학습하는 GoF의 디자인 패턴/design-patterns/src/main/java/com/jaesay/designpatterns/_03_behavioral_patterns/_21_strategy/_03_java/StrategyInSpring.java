package com.jaesay.designpatterns._03_behavioral_patterns._21_strategy._03_java;

import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

public class StrategyInSpring {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext();
        ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext();

        // 설정파일을 읽으면 BeanDefinition을 만들어야 한다.
        BeanDefinitionParser parser; // AnnotationConfig, ComponentScan

        // 여러 전략들을 빈으로 주입.. (기술에 따라 다른 빈을 주입)
        PlatformTransactionManager platformTransactionManager; // jdbc, jpa, hibernate 등등의 트랜잭션 매니저

        CacheManager cacheManager; // ehcache, jcache...
    }
}
