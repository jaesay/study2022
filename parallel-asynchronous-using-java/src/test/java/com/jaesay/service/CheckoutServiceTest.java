package com.jaesay.service;

import com.jaesay.domain.checkout.Cart;
import com.jaesay.domain.checkout.CheckoutResponse;
import com.jaesay.domain.checkout.CheckoutStatus;
import com.jaesay.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService  = new CheckoutService(priceValidatorService);

    @Test
    void checkNumberOfCores() {
        System.out.println("number of cores : " + Runtime.getRuntime().availableProcessors());
    }

    @Test
    void checkout_6_items() {
        // given
        Cart cart = DataSet.createCart(6);

        // when
        CheckoutResponse response = checkoutService.checkout(cart);

        // then
        assertEquals(CheckoutStatus.SUCCESS, response.getCheckoutStatus());
        assertTrue(response.getFinalRate() > 0);
    }

    @Test
    void checkout_13_items() {
        // given
        Cart cart = DataSet.createCart(13);

        // when
        // 코어 수 보다 많기 때문에 모든 sub task가 parallel하게 실행되지 않음
        CheckoutResponse response = checkoutService.checkout(cart);

        // then
        assertEquals(CheckoutStatus.FAILURE, response.getCheckoutStatus());
    }
}