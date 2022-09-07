package jaesay.inflearn._02_array;

import java.util.Scanner;

public class Main10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        Main10 main = new Main10();
        System.out.println(main.solution(n, arr));
    }

    private int solution(int n, int[][] arr) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int max = arr[i][j];
                boolean cheak = true;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx >= 0 && ny >=0 && nx < n && ny < n && max <= arr[nx][ny]) {
                        cheak = false;
                        break;
                    }
                }
                if (cheak) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
