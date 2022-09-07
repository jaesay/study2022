package jaesay.inflearn._07_recursive_tree_graph;

import java.util.LinkedList;
import java.util.Queue;

public class Main10 {

    private static int bfs(Node root) {
        Queue<Node> q = new LinkedList<>();
        int l = 0;
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (cur.lt == null && cur.rt == null) {
                    return l;
                }
                if (cur.lt != null) {
                    q.offer(cur.lt);
                }
                if (cur.rt != null) {
                    q.offer(cur.rt);
                }
            }
            l++;
        }
        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.lt = new Node(2);
        root.rt = new Node(3);
        root.lt.lt = new Node(4);
        root.lt.rt = new Node(5);
        System.out.println(bfs(root));
    }

    static class Node {
        int data;
        Node lt, rt;

        public Node(int val) {
            data = val;
            lt = rt = null;
        }
    }
}
