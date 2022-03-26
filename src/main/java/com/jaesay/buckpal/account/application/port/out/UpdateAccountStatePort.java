package com.jaesay.buckpal.account.application.port.out;

import com.jaesay.buckpal.account.domain.Account;

// 매핑하지 않기 전략
public interface UpdateAccountStatePort {
    void updateActivities(Account account);
}
