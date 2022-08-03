package _04_hashset_treeset;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();

        Main04 main = new Main04();
        System.out.println(main.solution(str1, str2));
    }

    private int solution(String str, String t) {
        Map<Character, Integer> target = new HashMap<>();
        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> map = new HashMap<>();
        int l = t.length() - 1;
        for (int i = 0; i < l; i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        int lt = 0;
        int answer = 0;
        for (int rt = l; rt < str.length(); rt++) {
            map.put(str.charAt(rt), map.getOrDefault(str.charAt(rt), 0) + 1);
            if (target.equals(map)) {
                answer++;
            }
            map.put(str.charAt(lt), map.get(str.charAt(lt)) - 1);
            if (map.get(str.charAt(lt)) == 0) {
                map.remove(str.charAt(lt));
            }
            lt++;
        }

        return answer;
    }
}
