package chapter6.item38;

public enum ExtendedOperation implements Operation {
    // 개별 인스턴스 수준에서 사용
    // 인터페이스 덕분에 따로 추상메서드를 선언하지 않아도 된다.
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
