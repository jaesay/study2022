package jaesay.inflearn._04_hashset_treeset;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        Main02 main = new Main02();
        System.out.println(main.solution(a, b));
    }

    private String solution(String a, String b) {
        Map<Character, Integer> aMap = new HashMap<>();
        Map<Character, Integer> bMap = new HashMap<>();
        for (char c : a.toCharArray()) {
            aMap.put(c, aMap.getOrDefault(c, 0) + 1);
        }

        for (char c : b.toCharArray()) {
            bMap.put(c, bMap.getOrDefault(c, 0) + 1);
        }

        if (aMap.equals(bMap)) {
            return "YES";
        } else {
            return "NO";
        }
    }
}
