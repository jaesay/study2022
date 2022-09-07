package chapter6.item34;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

// 상수 별 클래스 몸체(class body)와 데이터를 사용한 열거 타입
// 상수별 메서드 구현을 활용한 열거 타입 + 열거타입 상수 각각을 특정 데이터와 연결
public enum Operation {
    PLUS("+") { // <= 한개의 인스턴스로 생성, 한개의 클래스 선언

        @Override // 여기서는 생략해도 됨
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    // Operation 상수가 stringToEnum Map에 추가되는 시점은 열거 타입 상수 생성 후 정적 필드가 초기화 될 때다.
    private static final Map<String, Operation> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e -> e));

    // private
    // 맴버 클래스를 생성 등 enum은 effectively static
    Operation(String symbol) {
        this.symbol = symbol;
    }

    public abstract double apply(double x, double y);

    @Override
    public String toString() {
        return symbol;
    }

    // toString이 반환하는 문자열을 해당 열거 타입 상수로 변환해주는 fromString 메서드도 함꼐 제공하는 걸 고려해보자
    // e.g. 지정한 문자열에 해당하는 Operation을 (존재한다면) 반환한다.
    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }
}
