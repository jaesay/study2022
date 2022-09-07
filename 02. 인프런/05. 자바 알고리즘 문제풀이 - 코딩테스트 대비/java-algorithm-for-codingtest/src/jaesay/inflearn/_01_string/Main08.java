package jaesay.inflearn._01_string;

import java.util.Scanner;

public class Main08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        String reverse = new StringBuilder(str).reverse().toString();

        if (str.equals(reverse)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
