package _11_dynamicprogramming;

import java.util.Scanner;

public class Main01 {
    private static int[] dy;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Main01 main01 = new Main01();
        System.out.println(main01.solution(n));
    }

    private int solution(int n) {
        dy = new int[n + 1];
        dy[1] = 1;
        dy[2] = 2;
        for (int i = 3; i <= n; i++) {
            dy[i] = dy[i-1] + dy[i-2];
        }
        return dy[n];
    }
}
