package com.jaesay.designpatterns._01_creational_patterns._02_factory_method._02_after;

// 자바 8
public interface ShipFactory2 {

    default Ship orderShip(String name, String email) {
        validate(name, email);
        prepareFor(name);
        Ship ship = createShip();
        sendEmailTo(email, ship);

        return ship;
    }

    void sendEmailTo(String email, Ship ship);

    Ship createShip();

    void prepareFor(String name);

    void validate(String name, String email);
}
