package com.jaesay.designpatterns._03_behavioral_patterns._18_memento._02_after;

import java.io.Serializable;

public class Game implements Serializable {

    private int redTeamScore;

    private int blueTeamScore;

    public int getRedTeamScore() {
        return redTeamScore;
    }

    public void setRedTeamScore(int redTeamScore) {
        this.redTeamScore = redTeamScore;
    }

    public int getBlueTeamScore() {
        return blueTeamScore;
    }

    public void setBlueTeamScore(int blueTeamScore) {
        this.blueTeamScore = blueTeamScore;
    }

    public SavedGame save() {
        return new SavedGame(this.blueTeamScore, this.redTeamScore);
    }

    public void restore(SavedGame savedGame) {
        this.blueTeamScore = savedGame.getBlueTeamScore();
        this.redTeamScore = savedGame.getRedTeamScore();
    }

}
