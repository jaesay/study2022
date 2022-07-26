package chapter7.item45;

public class Card {
    private SUIT suit;
    private RANK rank;

    public Card(SUIT suit, RANK rank) {
        this.suit = suit;
        this.rank = rank;
    }

    enum RANK {
        ONE, TWO, THREE
    }

    enum SUIT {
        HEART, DIAMOND, CLOVER, SPADE
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
