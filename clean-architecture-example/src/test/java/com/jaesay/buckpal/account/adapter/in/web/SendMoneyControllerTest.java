package com.jaesay.buckpal.account.adapter.in.web;

import com.jaesay.buckpal.account.application.port.in.SendMoneyCommand;
import com.jaesay.buckpal.account.application.port.in.SendMoneyUseCase;
import com.jaesay.buckpal.account.domain.Account.AccountId;
import com.jaesay.buckpal.account.domain.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SendMoneyController.class)
class SendMoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SendMoneyUseCase sendMoneyUseCase;

    @Test
    void testSendMoney() throws Exception {
        mockMvc.perform(post("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}", 1L, 2L, 500L)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        then(sendMoneyUseCase)
                .should()
                .sendMoney(eq(new SendMoneyCommand(new AccountId(1L), new AccountId(2L), Money.of(500L))));
    }
}