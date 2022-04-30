package com.jaesay.buckpal.account.application.port.out;

import com.jaesay.buckpal.account.domain.Account;

import java.time.LocalDateTime;

public interface LoadAccountPort {
    Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate);
}
