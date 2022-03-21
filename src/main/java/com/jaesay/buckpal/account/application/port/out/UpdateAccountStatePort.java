package com.jaesay.buckpal.account.application.port.out;

import com.jaesay.buckpal.account.domain.Account;

public interface UpdateAccountStatePort {
    void updateActivities(Account account);
}
