package com.jaesay.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._01_before;

public class Client {

    public static void main(String[] args) {
        Request request = new Request("무궁화 꽃이 피었습니다.");
        // 클라이언트가 구체적인 헨들러 타입은 모르게 처리하고 싶음
        // 요청을 보내는 쪽과 요청을 처리하는 쪽이 디커플링되도록 하고 싶음
        RequestHandler requestHandler = new LoggingRequestHandler();
        requestHandler.handler(request);
    }
}
