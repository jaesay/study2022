package _08_dfs_bfs;

import java.util.Scanner;

public class Main06 {
    private static int[] ch = null;
    private static int[] arr = null;
    private static int[] pm = null;
    private static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        ch = new int[n];
        pm = new int[m];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        dfs(0);
    }

    private static void dfs(int l) {
        if (l == m) {
            for (int i : pm) {
                System.out.print(i + " ");
            }
            System.out.println();
        } else {
            for (int i = 0; i < n; i++) {
                if (ch[i] == 0) {
                    ch[i] = 1;
                    pm[l] = arr[i];
                    dfs(l + 1);
                    ch[i] = 0;
                }
            }
        }
    }
}
