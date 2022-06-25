package _11_etc;

import java.util.*;

public class Main2583 {

    private static int[][] arr;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int m;
    private static int n;
    private static int sum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        int k = sc.nextInt();
        arr = new int[m][n];
        for (int i = 0; i < k; i++) {
            int lx = sc.nextInt();
            int ly = sc.nextInt();
            int rx = sc.nextInt();
            int ry = sc.nextInt();
            for (int j = ly; j < ry; j++) {
                for (int l = lx; l < rx; l++) {
                    arr[j][l] = 1;
                }
            }
        }

        int areaCount = 0;
        List<Integer> areas = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    areaCount++;
                    sum = 1;
                    arr[i][j] = 1;
                    dfs(i, j);
                    areas.add(sum);
                }
            }
        }
        System.out.println(areaCount);
        Collections.sort(areas);
        for (Integer area : areas) {
            System.out.print(area + " ");
        }
    }

    private static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0  && ny >= 0 && nx < m && ny < n && arr[nx][ny] == 0) {
                arr[nx][ny] = 1;
                sum++;
                dfs(nx, ny);
            }
        }
    }
}
