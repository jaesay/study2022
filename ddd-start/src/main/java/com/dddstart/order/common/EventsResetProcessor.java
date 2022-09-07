package com.dddstart.order.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Deprecated
@Aspect
@Order(0)
//@Component
public class EventsResetProcessor {
    private ThreadLocal<Integer> nestedCount = ThreadLocal.withInitial(() -> 0);

    @Around("execution(public * com.ddd..*Service.*(..))")
    public Object doReset(ProceedingJoinPoint joinPoint) throws Throwable {
        nestedCount.set(nestedCount.get() + 1);
        try {
            return joinPoint.proceed();
        } finally {
            nestedCount.set(nestedCount.get() - 1);
            if (nestedCount.get() == 0) {
                Events.reset();
            }
        }
    }
}
