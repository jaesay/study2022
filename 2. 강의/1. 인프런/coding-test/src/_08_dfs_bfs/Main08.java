package _08_dfs_bfs;

import java.util.Scanner;

public class Main08 {
    private static int[] counts = null, ch = null, pm = null;
    private static int[][] ncr = null;
    private static boolean flag = false;
    private static int n, f;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        f = sc.nextInt();
        ncr = new int[n][n];
        ch = new int[n + 1];
        pm = new int[n];
        counts = new int[n];
        for (int i = 0; i < n; i++) {
            counts[i] = combi(n - 1, i);
        }
        dfs(0, 0);
    }

    private static void dfs(int l, int sum) {
        if (flag) {
            return;
        }
        if (l == n) {
            if (sum == f) {
                for (int i : pm) {
                    System.out.print(i + " ");
                }
                flag = true;
            }
        } else {
            for (int i = 1; i <= n; i++) {
                if (ch[i] == 0) {
                    ch[i] = 1;
                    pm[l] = i;
                    dfs(l + 1, sum + (pm[l] * counts[l]));
                    ch[i] = 0;
                }
            }
        }
    }

    private static int combi(int n, int r) {
        if (ncr[n][r] > 0) {
            return ncr[n][r];
        }
        if (n == r || r == 0) {
            return 1;

        } else {
            ncr[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
            return ncr[n][r];
        }
    }
}
