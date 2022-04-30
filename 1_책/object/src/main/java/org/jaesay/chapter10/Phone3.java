package org.jaesay.chapter10;

import java.util.ArrayList;
import java.util.List;

public abstract class Phone3 {

    private double taxRate;
    private List<Call> calls = new ArrayList<>();

    public Phone3(double taxRate) {
        this.taxRate = taxRate;
    }

    public Money calculateFee() {
        Money result = Money.ZERO;

        for (Call call : calls) {
            result = result.plus(calculateCallFee(call));
        }

        return result.plus(result.times(taxRate));
    }

    abstract protected Money calculateCallFee(Call call);

    public void call(Call call) {
        calls.add(call);
    }

    public List<Call> getCalls() {
        return calls;
    }
}
