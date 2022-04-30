package com.jaesay.buckpal.account.application.port.in;

// 완전 매핑 전략
public interface SendMoneyUseCase {
    boolean sendMoney(SendMoneyCommand command);
}
