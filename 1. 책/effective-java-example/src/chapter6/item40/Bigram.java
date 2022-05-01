package chapter6.item40;

import java.util.HashSet;

public class Bigram {
    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bigram bigram = (Bigram) o;

        if (first != bigram.first) return false;
        return second == bigram.second;
    }

    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        HashSet<Bigram> set = new HashSet<>();
        for (int i=0; i < 10; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                set.add(new Bigram(ch, ch));
            }
        }
        System.out.println(set.size());
    }
}
