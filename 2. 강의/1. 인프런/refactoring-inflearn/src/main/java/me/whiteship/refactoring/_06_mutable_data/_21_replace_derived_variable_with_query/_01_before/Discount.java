package me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query._01_before;

public class Discount {

    private double discountedTotal; // 파생 변수
    private double discount;

    private double baseTotal;

    public Discount(double baseTotal) {
        this.baseTotal = baseTotal;
    }

    public double getDiscountedTotal() {
        return this.discountedTotal;
    }

    public void setDiscount(double number) {
        this.discount = number;
        this.discountedTotal = this.baseTotal - this.discount;
    }
}
