package _08_dfs_bfs;

import java.util.Scanner;

public class Main03 {
    private static int n, m;
    private static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] scores = new int[n];
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
            times[i] = sc.nextInt();
        }
        dfs(0, 0, 0, scores, times);
        System.out.print(answer);
    }

    private static void dfs(int i, int score, int time, int[] scores, int[] times) {
        if (time > m) return;
        if (i == n) {
            answer = Math.max(score, answer);
        } else {
            dfs(i+1, score + scores[i], time + times[i], scores, times);
            dfs(i+1, score, time, scores, times);
        }
    }

}
