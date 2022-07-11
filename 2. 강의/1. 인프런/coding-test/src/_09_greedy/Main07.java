package _09_greedy;

import java.util.*;

public class Main07 {
    private static int[] unf;

    private static int find(int v) {
        if (v == unf[v]) {
            return v;
        } else {
            unf[v] = find(unf[v]);
            return unf[v];
        }
    }

    private static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        int answer = 0;
        Collections.sort(edges);

        for (Edge edge : edges) {
            int fv1 = find(edge.v1);
            int fv2 = find(edge.v2);
            if (fv1 != fv2) {
                answer += edge.cost;
                union(edge.v1, edge.v2);
            }
        }
        System.out.println(answer);
    }

    public static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int cost;

        public Edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
