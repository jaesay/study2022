package jaesay.inflearn._08_dfs_bfs;

import java.util.Scanner;

public class Main10 {
    private static int answer = 0;
    private static final int L = 7;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[L + 1][L + 1];
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= L; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        board[1][1]=1;
        dfs(1, 1, board);
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int[][] board) {
        if (x == L && y == L) {
            answer++;
        } else {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 1 && nx <= 7 && ny >= 1 && ny <= 7 && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    dfs(nx, ny, board);
                    board[nx][ny] = 0;
                }
            }
        }
    }
}
