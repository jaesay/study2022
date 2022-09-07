package jaesay.inflearn._07_recursive_tree_graph;

// 부분집합 구하기(DFS)
public class Main06 {
    private static int n;
    private static int[] ch;

    private void dfs(int l) {
        if (l == n+1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <=n ; i++) {
                if (ch[i] == 1) {
                    sb.append(i).append(" ");
                }
            }
            if (sb.length() > 0) {
                System.out.println(sb);
            }
        } else {
            ch[l] = 1;
            dfs(l+1); // 사용한다 (왼쪽 노드)
            ch[l] = 0;
            dfs(l+1); // 사용안한다 (오른쪽 노드)
        }
    }

    public static void main(String[] args) {
        Main06 main06 = new Main06();
        n = 3;
        ch = new int[n+1];
        main06.dfs(1);
    }
}
