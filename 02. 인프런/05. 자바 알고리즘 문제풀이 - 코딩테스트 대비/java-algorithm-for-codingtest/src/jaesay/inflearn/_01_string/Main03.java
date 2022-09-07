package jaesay.inflearn._01_string;

import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] words = line.split(" ");
        int maxIdx = 0;
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > words[i-1].length()) {
                maxIdx = i;
            }
        }
        System.out.println(words[maxIdx]);
    }
}
