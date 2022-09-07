package jaesay.inflearn._05_stack_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        Main07 main = new Main07();
        System.out.println(main.solution(a, b));
    }

    private String solution(String a, String b) {
        Queue<Character> q = new LinkedList<>();
        for (char c : a.toCharArray()) {
            q.add(c);
        }

        for (char c : b.toCharArray()) {
            if (q.contains(c)) {
                if (c != q.poll()) {
                    return "NO";
                }
            }
        }

        if (!q.isEmpty()) {
            return "NO";
        }

        return "YES";
    }
}
