package _08_dfs_bfs;

import java.util.*;

public class Main12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 6
        int m = sc.nextInt(); // 4

        int[][] arr = new int[m + 1][n + 1];
        List<Point> points = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 1) {
                    points.add(new Point(i, j));
                }
            }
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Point> q = new LinkedList<>();
        for (Point point : points) {
            q.add(point);
        }
        while (!q.isEmpty()) {
            Point c = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if (nx >= 1 && ny >=1 && nx <= m && ny <= n && arr[nx][ny] == 0) {
                    arr[nx][ny] = arr[c.x][c.y] + 1;
                    q.add(new Point(nx, ny));
                }
            }
        }

        int answer = -1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                answer = Math.max(answer, arr[i][j]);
            }
        }
        System.out.println(answer -1);
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
