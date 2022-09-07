package jaesay.inflearn._08_dfs_bfs;

import java.util.Scanner;

// https://cote.inflearn.com/contest/10/problem/08-01
public class Main01 {
    private static int[] ch = null;
    private static String answer = "NO";
    private static int n;
    private static int total;
    private static boolean flag = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }
        dfs(0, 0, arr);
        System.out.println(answer);
    }

    private static void dfs(int l, int sum, int[] arr) {
        if (flag) return;
        if (sum > total/2) return;
        if (l == n) {
            if ((total - sum) == sum) {
                answer = "YES";
                flag = true;
            }
        } else {
            dfs(l+1, sum + arr[l], arr);
            dfs(l+1, sum, arr);
        }
    }
}
