package jaesay.inflearn._08_dfs_bfs;

import java.util.*;

public class Main05 {
    private static int n, m;
    private static List<Integer> arr = null;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        arr.sort(Collections.reverseOrder());
        m = sc.nextInt();
        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int l, int sum) {
        if (sum > m) {
            return;
        }
        if (l >= answer) {
            return;
        }

        if (sum == m) {
            answer = Math.min(l, answer);
        } else {
            for (int i = 0; i < n; i++) {
                dfs(l + 1, sum + arr.get(i));
            }
        }
    }
}
