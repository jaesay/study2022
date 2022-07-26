package _02_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main06 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Main06 main = new Main06();
        List<Integer> solution = main.solution(n, arr);
        for (int x : solution) {
            System.out.print(x + " ");
        }

    }

    private List<Integer> solution(int n, int[] arr) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int tmp = arr[i];
            int res = 0;
            while (tmp > 0) {
                int t = tmp % 10;
                res = res * 10 + t;
                tmp = tmp / 10;
            }
            if (isPrimeNumber(res)) {
                answer.add(res);
            }
        }
        return answer;
    }

    private boolean isPrimeNumber(int reverse) {
        if (reverse == 1) {
            return false;
        }

        for (int j = 2; j < reverse / 2; j++) {
            if (reverse % j == 0) {
                return false;
            }
        }

        return true;
    }
}
