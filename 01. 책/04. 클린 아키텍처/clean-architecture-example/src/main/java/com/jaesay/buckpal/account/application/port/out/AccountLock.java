package com.jaesay.buckpal.account.application.port.out;

import com.jaesay.buckpal.account.domain.Account.AccountId;

public interface AccountLock {
    void lockAccount(AccountId accountId);
    void releaseAccount(AccountId accountId);
}
