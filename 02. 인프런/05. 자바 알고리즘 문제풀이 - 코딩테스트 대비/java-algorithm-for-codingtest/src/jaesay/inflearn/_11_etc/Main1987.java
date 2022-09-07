package jaesay.inflearn._11_etc;

import java.util.Scanner;

public class Main1987 {
    private static int max = Integer.MIN_VALUE;
    private static int[][] map;
    private static final int[] alphas = new int[26];
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static int c;
    private static int r;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            String next = sc.next();
            for (int j = 0; j < c; j++) {
                map[i][j] = next.charAt(j) - 'A';
            }
        }

        dfs(0, 0, 1);
        System.out.println(max);
    }

    private static void dfs(int x, int y, int l) {
        max = Math.max(l, max);
        alphas[map[x][y]] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < r && ny < c && alphas[map[nx][ny]] == 0) {
                dfs(nx, ny, l + 1);
            }
        }
        alphas[map[x][y]] = 0;
    }
}
