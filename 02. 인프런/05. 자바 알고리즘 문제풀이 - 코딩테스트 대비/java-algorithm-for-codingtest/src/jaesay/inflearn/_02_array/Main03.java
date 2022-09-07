package jaesay.inflearn._02_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arps = new int[n];
        for (int i = 0; i < n; i++) {
            arps[i] = sc.nextInt();
        }

        int[] brps = new int[n];
        for (int i = 0; i < n; i++) {
            brps[i] = sc.nextInt();
        }

        Main03 main = new Main03();
        List<String> solution = main.solution(n, arps, brps);
        for (String s : solution) {
            System.out.println(s);
        }
    }

    private List<String> solution(int n, int[] arps, int[] brps) {
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((arps[i] == 1 && brps[i] == 2) || (arps[i] == 2 && brps[i] == 3) || (arps[i] == 3 && brps[i] == 1)) {
                answer.add("B");
            } else if (arps[i] == brps[i]) {
                answer.add("D");
            } else {
                answer.add("A");
            }
        }
        return answer;
    }
}
