package jaesay.inflearn._09_greedy;

import java.util.*;

public class Main04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Lecture> lectures = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            int d = sc.nextInt();
            lectures.add(new Lecture(m, d));
            max = Math.max(d, max);
        }
        Collections.sort(lectures);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int answer = 0;
        int j = 0;
        for (int i = max; i >= 1; i--) {
            for (; j < n; j++) {
                if (lectures.get(j).d < i) break;
                pq.offer(lectures.get(j).m);
            }
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }

    public static class Lecture implements Comparable<Lecture> {
        int m;
        int d;

        public Lecture(int m, int d) {
            this.m = m;
            this.d = d;
        }

        @Override
        public int compareTo(Lecture o) {
            return o.d - this.d;
        }
    }
}
