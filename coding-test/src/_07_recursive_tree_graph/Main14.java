package _07_recursive_tree_graph;

import java.util.*;

public class Main14 {
    /**
     * 1번 vertex 에서 index 번 vertex 까지의 최소거리
     * 2차원 최단거리는 level이 아니라 배열로 풀어야됨
     */
    private static int[] dis = null;
    private static int[] ch = null;
    private static List<List<Integer>> graph = new ArrayList<>();

    private static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        dis[v] = 0;
        ch[v] = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer cv = q.poll();
                for (Integer nv : graph.get(cv)) {
                    if (ch[nv] == 0) {
                        q.offer(nv);
                        dis[nv] = dis[cv] + 1;
                        ch[nv] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        dis = new int[n + 1];
        ch = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph.get(x).add(y);
        }
        bfs(1);
        for (int i = 2; i < dis.length; i++) {
            System.out.println(i + " : " + dis[i]);
        }
    }
}
