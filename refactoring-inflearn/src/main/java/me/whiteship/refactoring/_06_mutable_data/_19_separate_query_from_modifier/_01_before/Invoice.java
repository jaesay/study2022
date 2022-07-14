package me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier._01_before;

public class Invoice {

    private double amount;

    public Invoice(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
