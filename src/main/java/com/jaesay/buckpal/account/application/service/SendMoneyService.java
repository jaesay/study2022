package com.jaesay.buckpal.account.application.service;

import com.jaesay.buckpal.account.application.port.in.SendMoneyCommand;
import com.jaesay.buckpal.account.application.port.in.SendMoneyUseCase;
import com.jaesay.buckpal.account.application.port.out.LoadAccountPort;
import com.jaesay.buckpal.account.application.port.out.UpdateAccountStatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        // TODO: 비즈니스 규칙 검증
        // TODO: 모델 상태 조작
        // TODO: 출력값 반환
        return false;
    }
}
