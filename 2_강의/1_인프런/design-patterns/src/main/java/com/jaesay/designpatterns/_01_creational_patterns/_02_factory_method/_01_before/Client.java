package com.jaesay.designpatterns._01_creational_patterns._02_factory_method._01_before;

public class Client {

    public static void main(String[] args) {
        Ship whiteship = ShipFactory.orderShip("Whiteship", "jaesay@mail.com");
        System.out.println(whiteship);

        Ship blackship = ShipFactory.orderShip("Blackship", "jaesay@mail.com");
        System.out.println(blackship);
    }

}
