package com.jaesay.designpatterns._02_structural_patterns._08_composite._02_after;

public class Client {

    public static void main(String[] args) {
        Item doranBlade = new Item("도란검", 450);
        Item healPotion = new Item("체력 물약", 50);

        Bag bag = new Bag();
        bag.add(doranBlade);
        bag.add(healPotion);

        Client client = new Client();
        client.printPrice(doranBlade);
        client.printPrice(bag);
    }

    // 그룹이던 개별이던 모른채 동일하게 처리
    private void printPrice(Component component) {
        System.out.println(component.getPrice());
    }
}
