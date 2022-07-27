package _02_array;

import java.util.Scanner;

public class Main07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Main07 main = new Main07();
        System.out.println(main.solution(n, arr));
    }

    private int solution(int n, int[] arr) {
        int answer = 0;
        int cnt = 0;
        for (int i : arr) {
            if (i == 1) {
                cnt++;
                answer += cnt;
            } else {
                cnt = 0;
            }
        }
        return answer;
    }
}
