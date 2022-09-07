package jaesay.inflearn._07_recursive_tree_graph;

import java.util.Scanner;

public class Main12 {
    private static int n;
    private static int m;
    private static int[][] graph;
    private static int[] ch;
    private static int answer = 0;

    private static void bfs(int c) {
//        ch[c] = 1;
        if (c == n) {
            answer++;
        } else {
            for (int i = 1; i <= n; i++) {
                if (ch[i] == 0 && graph[c][i] == 1) {
                    ch[i] = 1;
                    bfs(i);
                    ch[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x][y] = 1;
        }
        ch = new int[n + 1];
        ch[1] = 1;
        bfs(1);
        System.out.print(answer);

//        for (int i = 1; i <= n ; i++) {
//            for (int j = 1; j <= n ; j++) {
//                System.out.print(graph[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}
