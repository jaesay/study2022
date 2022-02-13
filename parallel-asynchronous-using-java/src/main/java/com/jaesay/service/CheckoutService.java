package com.jaesay.service;

import com.jaesay.domain.checkout.Cart;
import com.jaesay.domain.checkout.CartItem;
import com.jaesay.domain.checkout.CheckoutResponse;
import com.jaesay.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.jaesay.util.CommonUtil.startTimer;
import static com.jaesay.util.CommonUtil.timeTaken;
import static com.jaesay.util.LoggerUtil.log;

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

        double finalPrice = calculateFinalPrice_collect(cart);
//        double finalPrice = calculateFinalPrice_reduce(cart);
        log("finalPrice = " + finalPrice);

        return new CheckoutResponse(CheckoutStatus.SUCCESS, finalPrice);
    }

    private double calculateFinalPrice_collect(Cart cart) {
        return cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private double calculateFinalPrice_reduce(Cart cart) {
        return cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .reduce(0.0, Double::sum);
    }
}
