package _08_dfs_bfs;

import java.util.Scanner;

// https://cote.inflearn.com/contest/10/problem/08-02
public class Main02 {
    private static int c, n;
    private static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        dfs(0, 0, arr);
        System.out.println(answer);
    }

    private static void dfs(int i, int sum, int[] arr) {
        if (sum > c) {
            return;
        }

        if (i == n) {
            answer = Math.max(answer, sum);

        } else {
            dfs(i + 1, sum + arr[i], arr);
            dfs(i + 1, sum, arr);
        }
    }
}
