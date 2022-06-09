package _07_recursive_tree_graph;

// 피보나치 수열 (메모이제이션)
public class Main04 {
    private static int[] f;
    public static void main(String[] args) {
        int n = 45;
        f = new int[n+1];
        System.out.println(dfs(n));
        for (int i = 1; i <= n ; i++) {
            System.out.print(f[i] + " ");
        }
    }

    private static int dfs(int n) {
        if (f[n] > 0) return f[n];
        if (n == 1) {
            f[n] = 1;
        } else if (n == 2) {
            f[n] = 1;
        } else {
            f[n] = dfs(n - 2) + dfs(n - 1);
        }
        return f[n];
    }
}
