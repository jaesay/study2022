package jaesay.inflearn._07_recursive_tree_graph;

public class Main02 {
    public static void main(String[] args) {
        Main02 main02 = new Main02();
        main02.dfs(11);
    }

    public void dfs(int n) {
        if (n == 0) {
            return;
        } else {
            dfs(n/2);
            System.out.print(n%2);
        }
    }
}
