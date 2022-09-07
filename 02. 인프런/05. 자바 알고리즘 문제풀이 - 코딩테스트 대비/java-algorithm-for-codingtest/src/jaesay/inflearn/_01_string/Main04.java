package jaesay.inflearn._01_string;

import java.util.Scanner;

public class Main04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }
        String[] reverseWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            reverseWords[i] = new StringBuilder(words[i]).reverse().toString();
        }
        for (String reverseWord : reverseWords) {
            System.out.println(reverseWord);
        }
    }
}
