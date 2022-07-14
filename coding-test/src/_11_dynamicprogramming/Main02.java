package _11_dynamicprogramming;

import java.util.Scanner;

public class Main02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main02 main02 = new Main02();
        System.out.println(main02.solution(n));
    }

    private int solution(int n) {
        int[] dy = new int[n + 1];
        dy[1] = 2;
        dy[2] = 3;
        for (int i = 3; i <= n; i++) {
            dy[i] = dy[i-1] + dy[i-2];
        }
        return dy[n];
    }
}
