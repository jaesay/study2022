package com.jaesay.buckpal.account.adapter.out.persistence;

import com.jaesay.buckpal.account.domain.Account;
import com.jaesay.buckpal.account.domain.Account.AccountId;
import com.jaesay.buckpal.account.domain.ActivityWindow;
import com.jaesay.buckpal.account.domain.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static com.jaesay.buckpal.common.AccountTestData.defaultAccount;
import static com.jaesay.buckpal.common.ActivityTestData.defaultActivity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({AccountPersistenceAdapter.class, AccountMapper.class})
class AccountPersistenceAdapterTest {

    @Autowired
    private AccountPersistenceAdapter adapter;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @Sql("AccountPersistenceAdapterTest.sql")
    void loadsAccount() {
        Account account = adapter.loadAccount(new AccountId(1L), LocalDateTime.of(2018, 8, 10, 0, 0));

        assertThat(account.getActivityWindow().getActivities()).hasSize(2);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(500));
    }

    @Test
    void updateActivities() {
        Account account = defaultAccount()
                .withBaselineBalance(Money.of(555L))
                .withActivityWindow(new ActivityWindow(
                        defaultActivity().withId(null).withMoney(Money.of(1L)).build()))
                .build();

        adapter.updateActivities(account);

        assertThat(activityRepository.count()).isEqualTo(1);
        assertThat(activityRepository.findAll().get(0).getAmount()).isEqualTo(1L);
    }
}