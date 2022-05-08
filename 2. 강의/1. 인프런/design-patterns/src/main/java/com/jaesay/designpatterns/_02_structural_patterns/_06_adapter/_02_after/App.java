package com.jaesay.designpatterns._02_structural_patterns._06_adapter._02_after;

import com.jaesay.designpatterns._02_structural_patterns._06_adapter._02_after.security.LoginHandler;
import com.jaesay.designpatterns._02_structural_patterns._06_adapter._02_after.security.UserDetailsService;

public class App {

    public static void main(String[] args) {
        // adaptee: AccountService
        AccountService accountService = new AccountService();
        // target: UserDetailsService, adapter: AccountUserDetailsService
        UserDetailsService userDetailsService = new AccountUserDetailsService(accountService);
        LoginHandler loginHandler = new LoginHandler(userDetailsService);
        String login = loginHandler.login("jaesay", "jaesay");
        System.out.println(login);
    }
}
