package _05_stack_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Main06 main = new Main06();
        System.out.println(main.solution(n, k));
    }

    private int solution(int n, int k) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }

        while (!q.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                q.add(q.poll());
            }
            q.poll();
            if (q.size() == 1) {
                answer = q.poll();
            }
        }

        return answer;
    }
}
