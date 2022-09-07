package jaesay.inflearn._04_hashset_treeset;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Main05 main = new Main05();
        System.out.println(main.solution(n, k, arr));
    }

    private int solution(int n, int k, int[] arr) {
        Set<Integer> treeSet = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int l = j + 1; l < n; l++) {
                    treeSet.add(arr[i] + arr[j] + arr[l]);
                }
            }
        }

        int answer = -1;
        int cnt = 0;
        for (int i : treeSet) {
            cnt++;
            if (cnt == k) {
                return i;
            }
        }

        return answer;
    }
}
