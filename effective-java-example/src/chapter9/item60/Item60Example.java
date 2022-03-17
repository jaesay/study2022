package chapter9.item60;

import java.math.BigDecimal;

public class Item60Example {

    public static void main(String[] args) {
        System.out.println(1.03 - 0.42);
        System.out.println(1.00 - 9 * 0.10);
//        example1();
//        example2();
        example3();
    }

    private static void example1() {
        double funds = 1.00;
        int itemsBought= 0;
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println("itemsBought = " + itemsBought);
        System.out.println("funds = " + funds);
    }

    private static void example2() {
        final BigDecimal TEN_CENTS = new BigDecimal(".10");

        int itemBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >=0; price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemBought++;
        }
        System.out.println("itemBought = " + itemBought);
        System.out.println("funds = " + funds);
    }

    private static void example3() {
        int itemsBought= 0;
        int funds = 100;
        for (double price = 10; funds >= price; price += 10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println("itemsBought = " + itemsBought);
        System.out.println("funds = " + funds);
    }
}
