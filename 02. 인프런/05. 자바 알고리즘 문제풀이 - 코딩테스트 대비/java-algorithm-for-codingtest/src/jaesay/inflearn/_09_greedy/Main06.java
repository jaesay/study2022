package jaesay.inflearn._09_greedy;

import java.util.Scanner;

// Union & Find (암기)
public class Main06 {
    private static int[] unf; // 집합을 표현하는 배열, idx: 학생번호, value: 집합 번호

    /**
     * 학생의 집합 번호 리턴
     * @param v 학생번호
     * @return 학생 집합 번호
     */
    public static int find(int v) {
        if (v == unf[v]) {
            return v;
        } else {
            unf[v] = find(unf[v]); // 경로 압축
            return unf[v];
        }
    }

    public  static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        unf = new int[n + 1];
        for (int i = 0; i < n; i++) {
            unf[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            union(a, b);
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        int fa = find(a);
        int fb = find(b);
        if (fa == fb) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
