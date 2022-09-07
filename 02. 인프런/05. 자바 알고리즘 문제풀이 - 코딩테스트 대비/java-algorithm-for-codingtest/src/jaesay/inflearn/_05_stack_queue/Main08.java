package jaesay.inflearn._05_stack_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Main08 main = new Main08();
        System.out.println(main.solution(n, m, arr));
    }

    private int solution(int n, int m, int[] arr) {
        Queue<Patient> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            q.offer(new Patient(arr[i], i));
        }

        int answer = 0;
        while(!q.isEmpty()) {
            Patient c = q.poll();
            boolean max = true;
            for (Patient p : q) {
                if (c.e < p.e) {
                    max = false;
                }
            }

            if (!max) {
                q.offer(c);
            } else {
                answer++;
                if (c.w == m) {
                    return answer;
                }
            }
        }
        return answer;
    }

    public static class Patient {
        public int e;
        public int w;

        public Patient(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}
