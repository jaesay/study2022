package com.jaesay.designpatterns._02_structural_patterns._11_flyweight._01_before;

public class Client {
    // 자주 바뀌지 않는 데이터가 너무 많을 경우..
    public static void main(String[] args) {
        Character c1 = new Character('h', "white", "Nanum", 12);
        Character c2 = new Character('e', "white", "Nanum", 12);
        Character c3 = new Character('l', "white", "Nanum", 12);
        Character c4 = new Character('l', "white", "Nanum", 12);
        Character c5 = new Character('o', "white", "Nanum", 12);
    }
}
