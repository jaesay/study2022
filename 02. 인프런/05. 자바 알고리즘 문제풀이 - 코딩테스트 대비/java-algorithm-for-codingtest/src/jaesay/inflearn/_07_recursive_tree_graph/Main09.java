package jaesay.inflearn._07_recursive_tree_graph;

public class Main09 {

    // 최단거리이기 때문에 원래는 bfs로
    public static int dfs(int ㅣ, Node root) {
        if (root.lt == null && root.rt == null) {
            return ㅣ;
        } else {
            return Math.min(dfs(ㅣ + 1, root.lt), dfs(ㅣ + 1, root.rt)); // 밑단 노드가 항상 두개있어야 됨
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.lt = new Node(2);
        root.rt = new Node(3);
        root.lt.lt = new Node(4);
        root.lt.rt = new Node(5);

        System.out.println(dfs(0, root));
    }

//    private static int min = -1;

//    private static void dfs(Node root, int l) {
//        if (root.lt == null && root.rt == null) {
//            if (min == -1) {
//                min = l;
//            }
//            if (min > l) {
//                min = l;
//            }
//        } else {
//            if (root.lt != null) {
//                dfs(root.lt, l+1);
//            }
//            if (root.rt != null) {
//                dfs(root.rt, l+1);
//            }
//        }
//    }

    static class Node {
        int data;
        Node lt, rt;

        public Node(int val) {
            data = val;
            lt = rt = null;
        }
    }
}
