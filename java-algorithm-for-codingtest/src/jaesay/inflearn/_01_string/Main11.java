package jaesay.inflearn._01_string;

import java.util.Scanner;

public class Main11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        StringBuilder answer = new StringBuilder();
        str = str + " ";
        int cnt = 1;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                cnt++;
            } else {
                answer.append(str.charAt(i));
                if (cnt > 1) {
                    answer.append(cnt);
                    cnt = 1;
                }
            }
        }
        System.out.println(answer);
    }
}
