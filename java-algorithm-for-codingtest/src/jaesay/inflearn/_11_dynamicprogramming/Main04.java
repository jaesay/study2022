package jaesay.inflearn._11_dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main04 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Brick> bricks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bricks.add(new Brick(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        Main04 main04 = new Main04();
        System.out.println(main04.solution(bricks));
    }

    private int solution(List<Brick> bricks) {
        Collections.sort(bricks);
        int[] dy = new int[bricks.size()];

        dy[0] = bricks.get(0).h;
        int answer = dy[0];
        for (int i = 1; i < bricks.size(); i++) {
            dy[i] = bricks.get(i).h;
            for (int j = i - 1; j >= 0; j--) {
                if (bricks.get(i).w  < bricks.get(j).w) {
                    dy[i] = Math.max(bricks.get(i).h + dy[j], dy[i]);
                }
            }
            answer = Math.max(dy[i], answer);
        }

        return answer;
    }

    public static class Brick implements Comparable<Brick> {
        int a;
        int h;
        int w;

        public Brick(int a, int h, int w) {
            this.a = a;
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Brick o) {
            return o.a - this.a;
        }
    }
}
