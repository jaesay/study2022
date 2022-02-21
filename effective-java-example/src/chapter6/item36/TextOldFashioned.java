package chapter6.item36;

// 옛날 방식
// 비트 열거 상수
public class TextOldFashioned {
    public static final int STYLE_BOLD = 1; // 1
    public static final int STYLE_ITALIC = 1 << 1; // 2
    public static final int STYLE_UNDERLINE = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8

    // 매개변수 styles는 0개 이상의 STYLE_ 상수를 비트별 OR한 값이다.
    // 비트 필드를 사용하면 비트별 연산을 사용해 합집합과 교집합 같은 집합 연산을 효율적으로 수행할 수 있다.
    // 정수 열거 상수의 단점 + 출력값 해석이 더 어려움, 모든 원소 순회, API 개발 시 최대 몇비트가 필요한지 미리 예측하여 int or long 선택
    public void applyStyles(int styles) {
    }
}
