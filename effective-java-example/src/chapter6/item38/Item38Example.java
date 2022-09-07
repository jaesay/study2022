package chapter6.item38;

import java.util.Collection;
import java.util.List;

public class Item38Example {
    public static void main(String[] args) {
        double x = 2;
        double y = 4;

        Operation exp = ExtendedOperation.EXP;
        System.out.println(exp.apply(2, 4));

//        test(ExtendedOperation.class, x, y);
        test(List.of(ExtendedOperation.values()), x, y);
    }

    // class 리터럴은 한정적 타입 토큰 역할
    // Class 객체는 열거 타입인 동시에 Operation의 하위타입
    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (var op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    // 여러 구현 타입의 연산을 조합해 호출 가능
    // 반면, 특정 연산에서는 EnumSet, EnumMap을 사용 못함 => ?가 Enum인지 알 수 없기 떄문
    private static void test(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
