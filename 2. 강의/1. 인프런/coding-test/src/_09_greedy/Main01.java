package _09_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Applicant> applicants = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            applicants.add(new Applicant(h, w));
        }

        Main01 main01 = new Main01();
        int answer = main01.solution(applicants);
        System.out.println(answer);
    }

    private int solution(List<Applicant> applicants) {
        Collections.sort(applicants);
        int max = Integer.MIN_VALUE;
        int answer = 0;
        for (Applicant applicant : applicants) {
            if (applicant.w > max) {
                max = applicant.w;
                answer++;
            }
        }
        return answer;
    }

    public static class Applicant implements Comparable<Applicant> {
        int h;
        int w;

        public Applicant(int h, int w) {
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Applicant o) {
            return o.h - this.h;
        }
    }
}
