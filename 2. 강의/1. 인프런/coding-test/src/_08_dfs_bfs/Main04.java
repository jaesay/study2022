package _08_dfs_bfs;

import java.util.Scanner;

public class Main04 {
    private static int[] arr;
    private static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[m];
        dfs(0);
    }

    private static void dfs(int l) {
        if (l == m) {
            for (int x : arr) {
                System.out.print(x + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= n; i++) {
                arr[l] = i;
                dfs(l+1);
            }
        }
    }
}
