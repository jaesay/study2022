package com.jaesay.parallelstream;

import com.jaesay.service.CheckoutService;
import com.jaesay.service.PriceValidatorService;
import com.jaesay.util.DataSet;

import java.util.concurrent.ForkJoinPool;

import static com.jaesay.util.LoggerUtil.log;

public class ParallelismAndThreadsInForkJoinPoolExample {

    public static void main(String[] args) {
        // worker thread = core 수는 같음
        // core 수 - 1 이 되는 이유는? parallel stream을 호출하는 쓰레드를 하나 사용 (main thread)
        // 하지만 main thread도 parallel computation을 위해서도 사용된다.
        // deadlock 시나리오를 피하기 위해
        log("Parallelism info : " + ForkJoinPool.getCommonPoolParallelism()); // core 수 - 1

        PriceValidatorService priceValidatorService = new PriceValidatorService();
        new CheckoutService(priceValidatorService).checkout(DataSet.createCart(13));
    }
}
