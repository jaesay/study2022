package jaesay.inflearn._03_twopointers_slidingwindow;

import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Main03 main = new Main03();
        System.out.print(main.solution(n, k, arr));
    }

    private int solution(int n, int k, int[] arr) {
        int answer = 0;
        for (int i = 0; i < k; i++) {
            answer += arr[i];
        }

        int sum = answer;
        for (int i = k; i < n; i++) {
            sum = sum - arr[i - k] + arr[i];
            answer = Math.max(sum, answer);
        }

        return answer;
    }
}
