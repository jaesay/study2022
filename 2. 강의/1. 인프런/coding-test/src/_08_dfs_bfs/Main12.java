package _08_dfs_bfs;

import java.util.*;

public class Main12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 6
        int m = sc.nextInt(); // 4

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] board = new int[m + 1][n + 1];
        int[][] dis = new int[m + 1][n + 1];
        Queue<Point> q = new LinkedList<>();

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = sc.nextInt();
                if (board[i][j] == 1) {
                    q.add(new Point(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Point c = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if (nx >= 1 && ny >= 1 && nx <= m && ny <= n && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    dis[nx][ny] = dis[c.x][c.y] + 1;
                    q.add(new Point(nx, ny));
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (board[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        int answer = -1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                answer = Math.max(answer, dis[i][j]);
            }
        }
        System.out.println(answer);
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
