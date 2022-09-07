package jaesay.inflearn._05_stack_queue;

import java.util.Scanner;
import java.util.Stack;

public class Main03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] board = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        int m = sc.nextInt();
        int[] moves = new int[m];
        for (int i = 0; i < m; i++) {
            moves[i] = sc.nextInt();
        }

        Main03 main = new Main03();
        System.out.println(main.solution(board, moves));
    }

    private int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            int x = pop(move, board);
            if (!stack.isEmpty() && stack.peek() == x) {
                stack.pop();
                answer += 2;
            } else {
                stack.push(x);
            }
        }

        return answer;
    }

    private int pop(int move, int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][move] != 0) {
                int result = board[i][move];
                board[i][move] = 0;
                return result;
            }
        }
        return 0;
    }
}
