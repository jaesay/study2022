package _11_dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Main03 main03 = new Main03();
        System.out.println(main03.solution(n, arr));
    }

    private int solution(int n, int[] arr) {
        int[] dy = new int[n];
        Arrays.fill(dy, 1);
        for (int i = 1; i < n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dy[i] = Math.max(dy[i], dy[j] + 1);
                }
            }
        }
        int max = 0;
        for (int v : dy) {
            max = Math.max(max, v);
        }
        return max;
    }
}
