package org.jaesay.chapter04;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Money calculateFee(int audienceCount) {
        switch (this.movie.getMovieType()) {
            case AMOUNT_DISCOUNT:
                if (this.movie.isDiscountable(this.whenScreened, this.sequence)) {
                    return this.movie.calculateAmountDiscountedFee().times(audienceCount);
                }
            case PERCENT_DISCOUNT:
                if (this.movie.isDiscountable(this.whenScreened, this.sequence)) {
                    return this.movie.calculatePercentDiscountedFee().times(audienceCount);
                }
            case NONE_DISCOUNT:
                return this.movie.calculateNoneDiscountedFee().times(audienceCount);
        }

        return movie.calculateNoneDiscountedFee().times(audienceCount);
    }
}
