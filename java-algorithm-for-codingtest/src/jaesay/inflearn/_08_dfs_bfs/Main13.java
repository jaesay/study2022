package jaesay.inflearn._08_dfs_bfs;

import java.util.Scanner;

public class Main13 {

    private static final int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    private static int answer = 0;
    private static int[][] arr;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    arr[i][j] = 0;
                    dfs(i, j);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n && arr[nx][ny] == 1) {
                arr[nx][ny] = 0;
                dfs(nx, ny);
            }
        }
    }
}
