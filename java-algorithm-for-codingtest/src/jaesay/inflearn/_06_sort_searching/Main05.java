package jaesay.inflearn._06_sort_searching;

import java.util.Arrays;
import java.util.Scanner;

public class Main05 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    System.out.println(solution(n, arr));
  }

  private static String solution(int n, int[] arr) {
    Arrays.sort(arr);
    for (int i = 0; i < n - 1; i++) {
      if (arr[i] == arr[i+1]) {
        return "D";
      }
    }
    return "U";
  }
}
