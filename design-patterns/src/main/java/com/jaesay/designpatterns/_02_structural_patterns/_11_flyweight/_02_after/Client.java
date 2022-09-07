package com.jaesay.designpatterns._02_structural_patterns._11_flyweight._02_after;

public class Client {
    public static void main(String[] args) {
        FontFactory fontFactory = new FontFactory();
        Character c1 = new Character('a', "red", fontFactory.getFont("nanum:12"));
        Character c2 = new Character('b', "yellow", fontFactory.getFont("nanum:12"));
        Character c3 = new Character('c', "blue", fontFactory.getFont("nanum:12"));
    }
}
