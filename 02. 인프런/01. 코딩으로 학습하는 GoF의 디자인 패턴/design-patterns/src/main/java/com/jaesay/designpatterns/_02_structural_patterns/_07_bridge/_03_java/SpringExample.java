package com.jaesay.designpatterns._02_structural_patterns._07_bridge._03_java;

import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;

public class SpringExample {

    // 스프링에서 구체적인 것에 해당하는 interface를 많이 만들어놨다. (PSA)
    public static void main(String[] args) {
        // MailSender => implementation, JavaMailSenderImpl => concrete implementation
        MailSender mailSender = new JavaMailSenderImpl();

        PlatformTransactionManager platformTransactionManager = new JdbcTransactionManager();
    }
}
