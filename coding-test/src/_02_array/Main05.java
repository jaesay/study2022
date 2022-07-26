package _02_array;

import java.util.Scanner;

public class Main05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main05 main = new Main05();
        System.out.println(main.solution(n));
    }

    private int solution(int n) {
        int[] arr = new int[n + 1];
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (arr[i] == 0) {
                answer++;
                for (int j = i; j <= n; j+=i) {
                    arr[j] = 1;
                }
            }
        }
        return answer;
    }
}
