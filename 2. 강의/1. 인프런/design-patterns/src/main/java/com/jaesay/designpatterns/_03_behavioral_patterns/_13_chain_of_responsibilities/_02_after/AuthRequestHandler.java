package com.jaesay.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._02_after;

import com.jaesay.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._01_before.Request;

public class AuthRequestHandler extends RequestHandler {

    public AuthRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Request request) {
        System.out.println("인증이 되었는가?");
        System.out.println("조건을 걸어서 여기는 타지 않게, 체인들의 순서를 정할 수 있게..");
        super.handle(request);
        System.out.println("끝나고 돌아올떄 어떤일..");
    }
}
