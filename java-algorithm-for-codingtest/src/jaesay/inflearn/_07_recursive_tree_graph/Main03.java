package jaesay.inflearn._07_recursive_tree_graph;

public class Main03 {
    public static void main(String[] args) {
        System.out.println("dfs(5) = " + dfs(5));
    }

    private static int dfs(int n) {
        if (n == 1) {
            return 1;
        }
        return dfs(n-1) * n;
    }
}
