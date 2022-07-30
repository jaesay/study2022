package _02_array;

import java.util.Scanner;

public class Main11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        Main11 main = new Main11();
        System.out.println(main.solution(n, arr));
    }

    private int solution(int n, int[][] arr) {
        int answer = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int[] check = new int[n];
            check[i] = 1;
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < n; k++) {
                    if (arr[i][j] == arr[k][j] && check[k] == 0) {
                        sum++;
                        check[k] = 1;
                    }
                }
            }
            if (sum > max) {
                max = sum;
                answer = i;
            }
        }

        return answer + 1;
    }
}
