package _07_recursive_tree_graph;

public class Main01 {
    // recursive, dfs, backtracking
    public static void main(String[] args) {
        Main01 main01 = new Main01();
        main01.dfs(3);
    }

    public void dfs(int n) {
        if (n == 0) {
            return;
        } else {
            dfs(n-1);
            System.out.print(n + " ");
        }
    }
}
