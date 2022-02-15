package chapter6.item34;

// 필요한 원소를 컴파일 타임에 다 알 수 있는 상수 집합이라면 항상 열거 타입을 사용하자.
public class Item34Example {

    public static void main(String[] args) {
        Apple fuji1 = Apple.FUJI;
        Apple fuji2 = Apple.FUJI;

        // 각각의 상수는 싱글톤
        System.out.println("(fuji1 == fuji2) = " + (fuji1 == fuji2));

        double earthWeight = 185;
        double mass = earthWeight / Planet.EARTH.getSurfaceGravity();
        // 열거 타입은 자신 안에 정의된 상수들의 값을 배열에 담아 반환하는 정적 메서드인 values를 제공
        for (var p : Planet.values()) {
            // 각 열거 타입 값의 toString 메서드는 상수 이름을 문자열로 반환
            System.out.printf("%s에서의 무게는 %f이다.%n", p, p.surfaceWeight(mass));
        }

        double x = 2;
        double y = 4;

        for (var op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }

        // 열거 타입에는 상수 이름을 입력받아 그 이름에 해당하는 상수를 반환해주는 valueOf(String) 메서드가 자동 생성된다.
        Operation plus = Operation.valueOf("PLUS");
        System.out.println("Operation.valueOf(\"PLUS\") = " + plus);

        int fridayPay = PayrollDay.FRIDAY.pay(30, 100_000);
        System.out.println("fridayPay = " + fridayPay);
        int sundayPay = PayrollDay.SUNDAY.pay(30, 100_000);
        System.out.println("sundayPay = " + sundayPay);
    }

    // PayrollDay에서 확인한 것처럼 switch 문은 열거타입의 상수별 동작을 구현하는 데 적합하지 않다.
    // 하지만 기존 열거 타입에 상수별 동작을 혼합해 넣을 때는 switch 문이 좋은 선택이 될 수 있다.
    // e.g. 서드파티에서 가져온 Operation 열거 타입이 있는데, 각 연산의 반대 연산을 반환하는 메서드가 필요하다고 해보자.
    // switch 문을 이용해 원래 열거타입에 없는 기능을 수행한다.
    // =>
    // 1. 기존 열거 타입에 상수별 동작을 혼합해 넣을 때
    // 2. 추가하려는 메서드가 의미상 열거 타입에 속하지 않는다면 직접 만든 열거 타입이라도 이 방식을 적용하는 게 좋다.
    // 3. 종종 쓰이지만 열거 타입 안에 포함할 만큼 유용하지는 않은 경우도 마찬가지다.
    public static Operation inverse(Operation op) {
        switch (op) {
            case PLUS: return Operation.MINUS;
            case MINUS: return Operation.PLUS;
            case TIMES: return Operation.DIVIDE;
            case DIVIDE: return Operation.TIMES;

            default: throw new AssertionError("알 수 없는 연산: " + op);
        }
    }
}
