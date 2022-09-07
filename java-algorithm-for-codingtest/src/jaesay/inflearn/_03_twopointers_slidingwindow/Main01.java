package jaesay.inflearn._03_twopointers_slidingwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }

        Main01 main = new Main01();
        List<Integer> solution = main.solution(n, arr1, m, arr2);
        solution.forEach(i -> System.out.print(i + " "));
    }

    private List<Integer> solution(int n, int[] arr1, int m, int[] arr2) {
        List<Integer> answer = new ArrayList<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < n && p2 < m) {
            if (arr1[p1] <= arr2[p2]) {
                answer.add(arr1[p1]);
                p1++;
            } else {
                answer.add(arr2[p2]);
                p2++;
            }
        }

        for (int i = p1; i < n; i++) {
            answer.add(arr1[i]);
        }

        for (int i = p2; i < m; i++) {
            answer.add(arr2[i]);
        }

        return answer;
    }
}
