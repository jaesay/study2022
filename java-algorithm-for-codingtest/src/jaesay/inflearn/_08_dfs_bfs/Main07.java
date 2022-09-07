package jaesay.inflearn._08_dfs_bfs;

import java.util.Scanner;

public class Main07 {
    private static int[][] ncr = new int[34][34];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        System.out.println(dfs(n, r));
    }

    private static int dfs(int n, int r) {
        if (ncr[n][r] > 0) {
            return ncr[n][r];
        }
        if (n == r || r == 0) {
            return 1;
        } else {
            ncr[n][r] = dfs(n - 1, r - 1) + dfs(n - 1, r);
            return ncr[n][r];
        }
    }
}
