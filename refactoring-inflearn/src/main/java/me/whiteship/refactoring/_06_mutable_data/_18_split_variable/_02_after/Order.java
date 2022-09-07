package me.whiteship.refactoring._06_mutable_data._18_split_variable._02_after;

public class Order {

    public double discount(double inputValue, int quantity) {
        double result = inputValue;

        if (inputValue > 50) result = inputValue - 2;
        if (quantity > 100) result = inputValue - 1;

        return result;
    }
}
