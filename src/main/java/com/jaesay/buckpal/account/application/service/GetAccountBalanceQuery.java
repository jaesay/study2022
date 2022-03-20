package com.jaesay.buckpal.account.application.service;

import com.jaesay.buckpal.account.domain.Account.AccountId;
import com.jaesay.buckpal.account.domain.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);
}
