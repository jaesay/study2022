package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform._02_after;

public class Client1 extends ReadingClient {

    double baseCharge;

    public Client1(Reading reading) {
        this.baseCharge = enrichReading(reading).baseCharge();
    }

    public double getBaseCharge() {
        return baseCharge;
    }
}
