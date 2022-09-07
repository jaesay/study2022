package jaesay.inflearn._08_dfs_bfs;

import java.util.Scanner;

public class Main09 {
    private static int n;
    private static int m;
    private static int[] a;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[m];

        dfs(0, 1);
    }

    private static void dfs(int l, int s) {
        if (l == m) {
            for (int i : a) {
                System.out.print(i + " ");
            }
            System.out.println();
        } else {
            for (int i = s; i <= n; i++) {
                a[l] = i;
                dfs(l+1, i+1);
            }
        }
    }
}
