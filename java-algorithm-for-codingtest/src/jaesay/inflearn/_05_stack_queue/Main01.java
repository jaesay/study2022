package jaesay.inflearn._05_stack_queue;

import java.util.Scanner;
import java.util.Stack;

public class Main01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String braces = sc.next();
        Main01 main = new Main01();
        System.out.println(main.solution(braces));
    }

    private String solution(String braces) {
        Stack<Character> stack = new Stack<>();

        for (char c : braces.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return "NO";
                }
                stack.pop();

            }
        }

        if (stack.isEmpty()) {
            return "YES";
        }
        return "NO";
    }
}
