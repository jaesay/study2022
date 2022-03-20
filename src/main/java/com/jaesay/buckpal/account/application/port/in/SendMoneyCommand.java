package com.jaesay.buckpal.account.application.port.in;

import com.jaesay.buckpal.account.domain.Account.AccountId;
import com.jaesay.buckpal.account.domain.Money;
import com.jaesay.buckpal.common.SelfValidating;
import lombok.Getter;

@Getter
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

    private final AccountId sourceAccountId;
    private final AccountId targetAccountId;
    private final Money money;

    public SendMoneyCommand(AccountId sourceAccountId, AccountId targetAccountId, Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        this.validateSelf(); // 유효성 검사
    }
}
