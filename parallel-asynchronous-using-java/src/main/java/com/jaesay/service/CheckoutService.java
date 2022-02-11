package com.jaesay.service;

import com.jaesay.domain.checkout.Cart;
import com.jaesay.domain.checkout.CartItem;
import com.jaesay.domain.checkout.CheckoutResponse;
import com.jaesay.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.jaesay.util.CommonUtil.startTimer;
import static com.jaesay.util.CommonUtil.timeTaken;

public class CheckoutService {

    private final PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart) {
        startTimer();
        List<CartItem> priceValidationList = cart.getCartItemList()
                .parallelStream()
                .peek(cartItem -> {
                    boolean cartItemInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(cartItemInvalid);
                })
                .filter(CartItem::isExpired)
                .collect(Collectors.toList());

        timeTaken();
        if (priceValidationList.size() > 0) {
            return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
        }

        return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }
}
