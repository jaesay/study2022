package com.jaesay.designpatterns._02_structural_patterns._10_facade._03_java;

import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;

public class SpringFacade {

    // 파사드 패턴 관점: 구체적인 기술을 뒤로 감춤
    // 브릿지 패턴 관점: 추상적인 것과 구체적인 것을 분리
    public static void main(String[] args) {
        MailSender mailSender = new JavaMailSenderImpl();

        PlatformTransactionManager platformTransactionManager = new JdbcTransactionManager();
    }
}
