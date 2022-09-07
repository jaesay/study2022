package jaesay.inflearn._11_etc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main2667 {
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            String[] split = line.split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        Main2667 main = new Main2667();
        main.solution(map, n);
    }

    private void solution(int[][] map, int n) {
        int areaCount = 0;
        List<Integer> houseCounts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    areaCount++;
                    map[i][j] = 0;
                    houseCounts.add(dfs(i, j, n, map));
                }
            }
        }
        System.out.println(areaCount);
        Collections.sort(houseCounts);
        houseCounts.forEach(System.out::println);
    }

    private int dfs(int x, int y, int n, int[][] map) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] == 1) {
                map[nx][ny] = 0;
                sum += dfs(nx, ny, n, map);
            }
        }
        return sum + 1;
    }
}
