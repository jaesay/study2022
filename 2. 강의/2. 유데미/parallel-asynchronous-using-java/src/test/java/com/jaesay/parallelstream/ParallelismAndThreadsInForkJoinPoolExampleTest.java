package com.jaesay.parallelstream;

import com.jaesay.domain.checkout.Cart;
import com.jaesay.domain.checkout.CheckoutResponse;
import com.jaesay.domain.checkout.CheckoutStatus;
import com.jaesay.service.CheckoutService;
import com.jaesay.service.PriceValidatorService;
import com.jaesay.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelismAndThreadsInForkJoinPoolExampleTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService  = new CheckoutService(priceValidatorService);

    @Test
    void modify_parallelism() {
        // given
        // parallelism은 전체 app or process에서 사용된다.
        // blocking call이 적을 경우 default parallelism을 사용 추천
        // 수정하고 싶다면 여러시도 후에 적절한 값을 찾아야 됨
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100");
        Cart cart = DataSet.createCart(100);

        // when
        CheckoutResponse response = checkoutService.checkout(cart);

        // then
        assertEquals(CheckoutStatus.FAILURE, response.getCheckoutStatus());
    }

}