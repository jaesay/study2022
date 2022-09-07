package jaesay.inflearn._11_dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class Main05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        Main05 main05 = new Main05();
        System.out.println(main05.solution(n, m, coins));
    }

    private int solution(int n, int m, int[] coins) {
        int[] dy = new int[m + 1]; // i 금액을 만드는데 드는 최소동전개수
        Arrays.fill(dy, Integer.MAX_VALUE);
        dy[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j<=m; j++) {
                dy[j] = Math.min(dy[j], dy[j-coins[i]] + 1);
            }
        }
        return dy[m];
    }
}
