package com.jaesay.designpatterns._02_structural_patterns._12_proxy._02_after;

public class GameServiceProxy implements GameService {

    private final GameService gameService;

    public GameServiceProxy(GameService gameService) {
        this.gameService = gameService;
    }


    @Override
    public void startGame() {
        System.out.println(">> 지연초기화, 로깅, 권한체크, 캐싱 등등..");
        gameService.startGame();
        System.out.println(">> 지연초기화, 로깅, 권한체크, 캐싱 등등..");
    }
}
