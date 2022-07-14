package chapter8.item50;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Item50Example {

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);
        System.out.println("period.getEnd() = " + period.getEnd());
        end.setYear(78); // period 내부 수정
        System.out.println("period.getEnd() = " + period.getEnd());

        Date start2 = new Date();
        Date end2 = new Date();
        Period2 period2 = new Period2(start2, end2);
        System.out.println("period2.getEnd() = " + period2.getEnd());
        period2.getEnd().setYear(78); // 접근자 메서드가 내부 가변정보를 직접 드러냄
        System.out.println("period2 = " + period2);
        System.out.println("period2.getEnd() = " + period2.getEnd());

        Date start3 = new Date();
        Date end3 = new Date();
        Period3 period3 = new Period3(start3, end3);
        System.out.println("period3.getEnd() = " + period3.getEnd());
        period3.getEnd().setYear(78);
        System.out.println("period3 = " + period3);
        System.out.println("period3.getEnd() = " + period3.getEnd());

        Set<Period> hashSet = new HashSet<>(); // 래퍼 내부의 set을 접근 가능하지만 그 영향을 오직 클라이언트 자신만 받게 된다.
        Set<Period> periods = new InstrumentedHashSet<>(hashSet);
    }
}
