package chapter6.item35;

public enum Ensemble {
    // 열거 타입 상수에 연결된 값은 ordinal 메서드로 얻지 말고, 인스턴스 필드를 사용하자.
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5), SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians;

    Ensemble(int numberOfMusicians) {
        this.numberOfMusicians = numberOfMusicians;
    }

    public int getNumberOfMusicians() {
        return numberOfMusicians;
    }

    // enum의 위치 값을 얻는 ordinal() 메소드는 사용하지 말자. => EnumSet, EnumMap 같이 열거 타입 기반의 범용 자료구조에 쓸 목적으로 설계되었다.
    // ordinal() 사용으로 인한 단점은 순서 문제 등..
//    public int numberOfMusicians() {
//        return ordinal() + 1;
//    }
}
