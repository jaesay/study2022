package chapter9.item61;

import java.util.Comparator;

public class Item61Example {
    private static Integer i;
    public static void main(String[] args) {
        Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);
        System.out.println(naturalOrder.compare(Integer.valueOf(128), Integer.valueOf(128))); // -128~127을 넣으면 cache된 값을 사용해서 identity가 같음

//        if (i == 42) System.out.println("NullPointerException 발생");
        Long sum = 0L;
        for (long i =0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println("sum = " + sum);
    }
}
