package org.jaesay.chapter05;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation();
    }

    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public Money getMovieFee() {
        return this.movie.getFee();
    }

    public int getSequence() {
        return this.sequence;
    }

    public LocalDateTime getWhenScreened() {
        return this.whenScreened;
    }

}
