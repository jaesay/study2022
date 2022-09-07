package jaesay.inflearn._11_etc;

import java.util.Arrays;
import java.util.Scanner;

public class Main1759 {
    private static char[] pass;
    private static int l, c;
    private static char[] alphas;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        c = sc.nextInt();

        pass = new char[l];
        alphas = new char[c];
        sc.nextLine();
        String[] strs = sc.nextLine().split(" ");
        for (int i = 0; i < c; i++) {
            alphas[i] = strs[i].charAt(0);
        }

        Arrays.sort(alphas);

        dfs(0, 0);
    }

    private static void dfs(int d, int s) {
        if (d == l) {
            int vowelCount = 0;
            int consonantCount = 0;
            for (char passChar : pass) {
                if (passChar == 'a' || passChar == 'e' || passChar == 'i' || passChar == 'o' || passChar == 'u') {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }

            if (vowelCount >= 1 && consonantCount >= 2) {
//                System.out.print("vowelCount: " + vowelCount + ", consonantCount: " + consonantCount + " => ");

                System.out.print(pass);
                System.out.println();
            }

        } else {
            for (int i = s; i < c; i++) {
                pass[d] = alphas[i];
                dfs(d + 1, i + 1);
            }
        }
    }
}
