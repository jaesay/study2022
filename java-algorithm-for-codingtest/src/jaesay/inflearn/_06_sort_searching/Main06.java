package jaesay.inflearn._06_sort_searching;

import java.util.ArrayList;
import java.util.Arrays;
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

    solution(n, arr).forEach(i -> System.out.print(i + " "));
  }

  private static List<Integer> solution(int n, int[] arr) {
    List<Integer> answer = new ArrayList<>();
    int[] clone = arr.clone();
    Arrays.sort(clone);
    for (int i = 0; i < n; i++) {
      if (arr[i] != clone[i]) {
        answer.add(i + 1);
      }
    }
    return answer;
  }
}
