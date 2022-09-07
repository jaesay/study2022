package jaesay.inflearn._07_recursive_tree_graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 정점의 개수가 많아지면 리스트로 (시간 & 공간복잡도)
public class Main13 {
    private static List<List<Integer>> graph = null;
    private static int[] ch;
    private static int n;
    private static int m;
    private static int answer = 0;

    private static void dfs(int v) {
        if (v == n) {
            answer++;
        } else {
            graph.get(v).forEach(nv -> {
                if (ch[nv] == 0) {
                    ch[nv] = 1;
                    dfs(nv);
                    ch[nv] = 0;
                }
            });
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 정점의 개수
        m = sc.nextInt(); // 간선의 개수
        graph = new ArrayList<>();
        ch = new int[n+1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph.get(x).add(y);
        }

        ch[1] = 1;
        dfs(1);
        System.out.println(answer);

//        for (int i = 1; i < graph.size(); i++) {
//            System.out.print(i + ": ");
//            graph.get(i).forEach(j -> System.out.print(j + " "));
//            System.out.println();
//        }
    }
}
