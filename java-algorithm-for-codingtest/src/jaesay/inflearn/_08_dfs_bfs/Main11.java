package jaesay.inflearn._08_dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main11 {
    private static final int L = 7;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] board;
    private static int[][] dis;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        board = new int[L + 1][L + 1];
        dis = new int[L + 1][L + 1];
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= L; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        bfs(1, 1);
        if (dis[L][L] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(dis[L][L]);
        }
    }

    private static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        board[x][y] = 1;
        while (!q.isEmpty()) {
            Point c = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if (nx >= 1 && nx <= L && ny >= 1 && ny <= L && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    q.add(new Point(nx, ny));
                    dis[nx][ny] = dis[c.x][c.y] + 1;
                }
            }
        }
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
