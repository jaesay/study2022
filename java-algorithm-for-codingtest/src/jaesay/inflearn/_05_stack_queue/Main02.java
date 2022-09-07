package jaesay.inflearn._05_stack_queue;

import java.util.Scanner;
import java.util.Stack;

public class Main02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        Main02 main = new Main02();
        System.out.println(main.solution(str));
    }

    private String solution(String str) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                stack.pop();
            } else {
                if (stack.isEmpty()) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
