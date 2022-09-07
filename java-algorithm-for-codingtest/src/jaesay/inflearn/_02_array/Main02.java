package jaesay.inflearn._02_array;

import java.util.Scanner;

public class Main02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        Main02 main = new Main02();
        System.out.println(main.solution(n, heights));
    }

    private int solution(int n, int[] heights) {
        int answer = 0;
        int max = Integer.MIN_VALUE;

        for (int height : heights) {
            if (height > max) {
                max = height;
                answer++;
            }
        }

        return answer;
    }
}
