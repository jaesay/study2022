package com.jaesay.buckpal.account.domain;

import com.jaesay.buckpal.account.domain.Account.AccountId;
import com.jaesay.buckpal.common.AccountTestData;
import com.jaesay.buckpal.common.ActivityTestData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void withdrawalSucceeds() {
        AccountId accountId = new AccountId(1L);
        Account account = AccountTestData.defaultAccount()
                .withAccountId(accountId)
                .withBaselineBalance(Money.of(555L))
                .withActivityWindow(new ActivityWindow(
                        ActivityTestData.defaultActivity()
                                .withTargetAccount(accountId)
                                .withMoney(Money.of(999L))
                                .build(),
                        ActivityTestData.defaultActivity()
                                .withTargetAccount(accountId)
                                .withMoney(Money.of(1L))
                                .build()
                )).build();

        boolean success = account.withdraw(Money.of(555L), new AccountId(99L));

        assertThat(success).isTrue();
        assertThat(account.getActivityWindow().getActivities()).hasSize(3);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(1000L));
    }

}