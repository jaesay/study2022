package _06_sort_searching;

import java.util.Scanner;

public class Main01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Main01 main = new Main01();
        for (int i : main.solution(n, arr)) {
            System.out.print(i + " ");
        }
    }

    private int[] solution(int n, int[] arr) {
        for (int i = 0; i < n - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[idx] > arr[j]) {
                    idx = j;
                }
            }

            int tmp = arr[idx];
            arr[idx] = arr[i];
            arr[i] = tmp;
        }

        return arr;
    }
}
