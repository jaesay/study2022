package com.jaesay.buckpal.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 실제 계좌의 snapshot 을 제공한다.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    @Getter
    private AccountId id;

    /**
     * activity window 의 첫 번째 활동 바로 전의 잔고
     */
    @Getter
    private Money baselineBalance;

    /**
     * 한 계좌에 대한 며칠 혹은 몇 주간의 범위에 해당하는 활동만 보유한다.
     */
    @Getter
    private final ActivityWindow activityWindow;

    public static Account withoutId(Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    public static Account withId(AccountId accountId, Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Money calculateBalance() {
        return Money.add(this.baselineBalance, this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {
        // 비즈니스 규칙 검증 => 이렇게 하면 이 규칙을 지켜야 하는 비즈니스 로직 바로 옆에 규칙이 위치하기 떄문에 위치를 정하는 것도 쉽고 추론하기도 쉽다.
        // 만약 도메인 엔티티에서 비즈니스 규칙을 검증하기가 여의치 않다면 유즈케아스 코드에서 도메인 엔티티를 사용하기 전에 해도 된다.
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = new Activity(this.id, this.id, targetAccountId, LocalDateTime.now(), money);
        this.activityWindow.addActivity(withdrawal);

        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(this.calculateBalance(), money.negate()).isPositive();
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = new Activity(this.id, sourceAccountId, this.id, LocalDateTime.now(), money);
        this.activityWindow.addActivity(deposit);
        return true;
    }

    @Value
    public static class AccountId {
        private Long value;
    }
}
