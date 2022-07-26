package _09_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Time> times = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            times.add(new Time(sc.nextInt(), 's'));
            times.add(new Time(sc.nextInt(), 'e'));
        }

        Main03 main03 = new Main03();
        System.out.println(main03.solution(times));
    }

    private int solution(List<Time> times) {
        int answer = Integer.MIN_VALUE;
        int cnt = 0;
        Collections.sort(times);
        for (Time time : times) {
            if (time.state == 's') {
                cnt++;
            } else {
                cnt--;
            }
            answer = Math.max(cnt, answer);
        }
        return answer;
    }

    public static class Time implements Comparable<Time> {
        int time;
        char state;

        public Time(int time, char state) {
            this.time = time;
            this.state = state;
        }

        @Override
        public int compareTo(Time o) {
            if (this.time == o.time) {
                return this.state - o.state;
            }
            return this.time - o.time;
        }
    }
}
