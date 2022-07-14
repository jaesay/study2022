package com.jaesay.designpatterns._02_structural_patterns._12_proxy._02_after;

public class Client {

    // 기존 코드를 건드리지 않고 새로운 기능을 추가하고 싶음
    public static void main(String[] args) throws InterruptedException {
        GameService gameService = new GameServiceProxy(new DefaultGameService());
        gameService.startGame();
    }
}
