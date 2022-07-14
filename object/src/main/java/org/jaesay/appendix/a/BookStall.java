package org.jaesay.appendix.a;

public class BookStall {
    public Book sell(IndependentPublisher independentPublisher) {
        return new Book(independentPublisher);
    }
}
