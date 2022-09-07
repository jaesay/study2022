package jaesay.inflearn._08_dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main14 {
    private static final int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    private static int answer = 0;
    private static int[][] arr;
    private static int n;
    private static Queue<Point> q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    q.add(new Point(i, j));
                    arr[i][j] = 0;
                    answer++;
                    bfs();
                }
            }
        }

        System.out.println(answer);
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Point c = q.poll();
            for (int i = 0; i < 8; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && arr[nx][ny] == 1) {
                    arr[nx][ny] = 0;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
