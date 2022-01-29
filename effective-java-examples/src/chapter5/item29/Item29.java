package chapter5.item29;

public class Item29 {
    public static void main(String[] args) {
        Item29_Stack2<String> stack  = new Item29_Stack2<>();
        for (String arg : args) {
            stack.push(arg);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toUpperCase());
        }
    }
}
