package com.jaesay.designpatterns._03_behavioral_patterns._18_memento._02_after;

public final class SavedGame {

    private final int blueTeamScore;

    private final int redTeamScore;

    public SavedGame(int blueTeamScore, int redTeamScore) {
        this.blueTeamScore = blueTeamScore;
        this.redTeamScore = redTeamScore;
    }

    public int getBlueTeamScore() {
        return blueTeamScore;
    }

    public int getRedTeamScore() {
        return redTeamScore;
    }
}
