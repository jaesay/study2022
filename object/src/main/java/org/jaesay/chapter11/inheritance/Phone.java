package org.jaesay.chapter11.inheritance;

import java.util.ArrayList;
import java.util.List;

public abstract class Phone {
    private List<Call> calls = new ArrayList<>();

    public Money calculateFee() {
        Money result = Money.ZERO;

        for (Call call : calls) {
            result = result.plus(calculateCallFee(call));
        }

        return afterCalculated(result);
    }

    // 훅메서드
    protected Money afterCalculated(Money fee) {
        return fee;
    }

    abstract protected Money calculateCallFee(Call call);

    public void call(Call call) {
        calls.add(call);
    }

    public List<Call> getCalls() {
        return calls;
    }
}
