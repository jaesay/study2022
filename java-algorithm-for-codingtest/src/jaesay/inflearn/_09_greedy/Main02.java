package jaesay.inflearn._09_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(sc.nextInt(), sc.nextInt()));
        }
        Main02 main02 = new Main02();
        System.out.println(main02.solution(meetings));
    }

    private int solution(List<Meeting> meetings) {
        Collections.sort(meetings);
        int maxEnd = Integer.MIN_VALUE;
        int answer = 0;
        for (Meeting meeting : meetings) {
            if (meeting.s >= maxEnd) {
                maxEnd = meeting.e;
                answer++;
            }
        }
        return answer;
    }

    public static class Meeting implements Comparable<Meeting> {
        int s;
        int e;

        public Meeting(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.e == o.e) {
                return this.s - o.s;
            }
            return this.e - o.e;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "s=" + s +
                    ", e=" + e +
                    '}';
        }
    }
}
