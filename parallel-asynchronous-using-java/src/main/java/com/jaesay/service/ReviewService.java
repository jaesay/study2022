package com.jaesay.service;

import com.jaesay.domain.Review;

import static com.jaesay.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
