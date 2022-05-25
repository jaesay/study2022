package com.jaesay.designpatterns._03_behavioral_patterns._18_memento._02_after;

public class Client {

    public static void main(String[] args) {
        Game game = new Game();
        game.setBlueTeamScore(10);
        game.setRedTeamScore(20);

        // 캽슐화, 한군데에서만 바꾸면 됨
        // 메모리 고려 필요
        SavedGame save = game.save();

        game.setBlueTeamScore(12);
        game.setRedTeamScore(22);

        game.restore(save);

        System.out.println(game.getBlueTeamScore());
        System.out.println(game.getRedTeamScore());
    }
}
