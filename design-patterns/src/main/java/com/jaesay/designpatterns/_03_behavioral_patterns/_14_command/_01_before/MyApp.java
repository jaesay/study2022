package com.jaesay.designpatterns._03_behavioral_patterns._14_command._01_before;

// invoker
public class MyApp {

    private Game game;

    public MyApp(Game game) {
        this.game = game;
    }

    public void press() {
        game.start();
    }

    // invoker에서 중복 코드가 많이 발생하고 잦은 변경 발생
    public static void main(String[] args) {
        Button button = new Button(new Light());
        button.press();
        button.press();
        button.press();
        button.press();
    }
}
