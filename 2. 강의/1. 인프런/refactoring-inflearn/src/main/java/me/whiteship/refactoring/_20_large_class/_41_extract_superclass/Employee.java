package me.whiteship.refactoring._20_large_class._41_extract_superclass;

public class Employee extends Party {

    private Integer id;

    private double monthlyCost;

    public Employee(String name) {
        super(name);
    }

    public Integer getId() {
        return id;
    }

    @Override
    protected double monthlyCost() {
        return monthlyCost;
    }
}
