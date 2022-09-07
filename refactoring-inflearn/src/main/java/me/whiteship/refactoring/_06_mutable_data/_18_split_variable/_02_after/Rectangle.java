package me.whiteship.refactoring._06_mutable_data._18_split_variable._02_after;

public class Rectangle {

    private double perimeter;
    private double area;

    public void updateGeometry(double height, double width) {
        double perimeter = 2 * (height + width);
        System.out.println("Perimeter: " + perimeter);
        this.perimeter = perimeter; // 지름

        double area = height * width;
        System.out.println("Area: " + area);
        this.area = area; // 넓이
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }
}
