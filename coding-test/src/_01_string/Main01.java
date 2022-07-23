package _01_string;

import java.util.Scanner;

public class Main01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next().toLowerCase();
        char findChar = sc.next().toLowerCase().charAt(0);

        int answer = 0;
        for (char c : word.toCharArray()) {
            if (c == findChar) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
