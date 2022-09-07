package com.jaesay.designpatterns._02_structural_patterns._06_adapter._02_after;

import com.jaesay.designpatterns._02_structural_patterns._06_adapter._02_after.security.UserDetails;
import com.jaesay.designpatterns._02_structural_patterns._06_adapter._02_after.security.UserDetailsService;

public class AccountUserDetailsService implements UserDetailsService {
    private final AccountService accountService;

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUser(String username) {
        return new AccountUserDetails(accountService.findAccountByUsername(username));
    }
}
