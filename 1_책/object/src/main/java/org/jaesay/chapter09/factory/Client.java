package org.jaesay.chapter09.factory;

import org.jaesay.chapter09.Money;
import org.jaesay.chapter09.Movie;

public class Client {
    private MovieFactory movieFactory;

    public Client(MovieFactory movieFactory) {
        this.movieFactory = movieFactory;
    }

    public Money getAvatarFee() {
        Movie avatar = movieFactory.createAvatarMovie();
        return avatar.getFee();
    }
}
