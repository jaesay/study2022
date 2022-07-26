package _01_string;

import java.util.Scanner;

public class Main09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        System.out.println(Integer.parseInt(sb.toString()));
    }
}
