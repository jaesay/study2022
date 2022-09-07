package jaesay.inflearn._02_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        Main01 main = new Main01();
        List<Integer> solution = main.solution(n, numbers);

        System.out.println(solution.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private List<Integer> solution(int n, int[] numbers) {
        List<Integer> answer = new ArrayList<>();
        answer.add(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[i-1]) {
                answer.add(numbers[i]);
            }
        }
        return answer;
    }
}
