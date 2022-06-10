package _07_recursive_tree_graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://cote.inflearn.com/contest/10/problem/07-08
public class Main08 {
    public static void main(String[] args) {
        Main08 main08 = new Main08();
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int e = sc.nextInt();
        System.out.print(main08.bfs(s, e));
    }

    private int bfs(int s, int e) {
        int[] jumps = {1, -1, 5};
        int[] ch = new int[10_001];
        int l = 0;
        Queue<Integer> q = new LinkedList<>();
        ch[s] = 1;
        q.offer(s);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer x = q.poll();
                for (int j = 0; j < jumps.length; j++) {
                    int nx = x + jumps[j];
                    if (nx == e) {
                        return l+1;
                    }
                    if (nx >= 1 && nx <= 10_000 && ch[nx] == 0) { // 방문한 곳은 더 작은 레벨에서 이미 탐색했기 떄문에 제외
                        ch[nx] = 1;
                        q.offer(nx);
                    }
                }
            }
            l++;
        }
        throw new IllegalStateException();
    }

}
