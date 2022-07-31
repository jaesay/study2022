package _03_twopointers_slidingwindow;

import java.util.Scanner;

public class Main05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main05 main = new Main05();
        System.out.println(main.solution(n));
    }

    private int solution(int n) {
        int lt = 1;
        int answer = 0;
        int sum = 0;
        for (int rt = 1; rt <= n/2 + 1; rt++) {
            sum += rt;
            if (sum == n) {
                answer++;
            }
            while (sum >= n) {
                sum -= lt;
                lt++;
                if (sum == n) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
