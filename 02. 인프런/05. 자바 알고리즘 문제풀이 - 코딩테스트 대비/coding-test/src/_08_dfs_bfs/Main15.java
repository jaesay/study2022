package _08_dfs_bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main15 {

    private static int m;
    static int[] combi;
    private static int answer = Integer.MAX_VALUE;
    private static List<Point> houses;
    private static List<Point> stores;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextInt();
        houses = new ArrayList<>();
        stores = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int value = sc.nextInt();
                if (value == 1) {
                    houses.add(new Point(i, j));
                }
                if (value == 2) {
                    stores.add(new Point(i, j));
                }
            }
        }

        combi = new int[m];
        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int l, int s) {
        if (l == m) {
            int sum = 0;
            for (Point house : houses) {
                int min = Integer.MAX_VALUE;
                for (int i : combi) {
                    min = Math.min(min, Math.abs(house.x - stores.get(i).x) + Math.abs(house.y - stores.get(i).y));
                }
                sum += min;
            }
            answer = Math.min(sum, answer);

        } else {
            for (int i = s; i < stores.size(); i++) {
                combi[l] = i;
                dfs(l + 1, i + 1);
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
