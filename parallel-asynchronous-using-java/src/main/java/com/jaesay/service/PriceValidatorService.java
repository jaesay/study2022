package com.jaesay.service;


import com.jaesay.domain.checkout.CartItem;

import static com.jaesay.util.CommonUtil.delay;
import static com.jaesay.util.LoggerUtil.log;

public class PriceValidatorService {

    public boolean isCartItemInvalid(CartItem cartItem){
        int cartId = cartItem.getItemId();
        log("isCartItemInvalid cartItem Id: " + cartId);
        delay(500);
        if (cartId == 7 || cartId == 9 || cartId == 11) {
            return true;
        }
        return false;
    }
}
