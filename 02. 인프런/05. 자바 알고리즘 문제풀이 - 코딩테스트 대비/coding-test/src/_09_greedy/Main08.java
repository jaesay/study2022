package _09_greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main08 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] ch = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c)); // 무방향 그래프
        }
        int answer = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int v = cur.vex;
            if (ch[v] == 0) { // 회로가 되는거 방지
                ch[v] = 1;
                answer += cur.cost;
                for (Edge next : graph.get(v)) {
                    if (ch[next.vex] == 0) { // 이미 지나간 경로 되돌아가는거 방지
                        pq.add(new Edge(next.vex, next.cost));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static class Edge implements Comparable<Edge> {
        int vex;
        int cost;

        public Edge(int vex, int cost) {
            this.vex = vex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
