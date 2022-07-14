package _07_recursive_tree_graph;

import java.util.*;

// 이진트리 레벨탐색
// 깊이우선탐색, BFS(Breadth-First Search), 레벨탐색
public class Main07 {
    Node root;

    public void bfs(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int l = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            System.out.print("level " + l + " : ");
            for (int i = 0; i < len; i++) {
                Node cur = q.poll();
                System.out.print(cur.data + " ");
                // 다음 레벨애들을 큐에 추가
                if (cur.lt != null) {
                    q.add(cur.lt);
                }
                if (cur.rt != null) {
                    q.add(cur.rt);
                }
            }
            l++;
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Main07 tree = new Main07();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.root.rt.lt = new Node(6);
        tree.root.rt.rt = new Node(7);
        tree.bfs(tree.root);
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
