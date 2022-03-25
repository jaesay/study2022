package com.jaesay.buckpal.account.application.service;

import com.jaesay.buckpal.account.application.port.out.AccountLock;
import com.jaesay.buckpal.account.domain.Account.AccountId;
import org.springframework.stereotype.Component;

@Component
public class NoOpAccountLock implements AccountLock {
    @Override
    public void lockAccount(AccountId accountId) {
        // do nothing
    }

    @Override
    public void releaseAccount(AccountId accountId) {
        // do nothing
    }
}
