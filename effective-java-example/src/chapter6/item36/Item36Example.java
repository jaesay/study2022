package chapter6.item36;

import java.util.EnumSet;

public class Item36Example {

    public static void main(String[] args) {
        TextOldFashioned textOldFashioned = new TextOldFashioned();
        textOldFashioned.applyStyles(TextOldFashioned.STYLE_BOLD | TextOldFashioned.STYLE_ITALIC); // 0011

        Text text = new Text();
        // EnumSet 클래스는 열거 타입 상수의 값으로 구성된 집합을 효과적으로 표현해준다.
        text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC));
    }
}
