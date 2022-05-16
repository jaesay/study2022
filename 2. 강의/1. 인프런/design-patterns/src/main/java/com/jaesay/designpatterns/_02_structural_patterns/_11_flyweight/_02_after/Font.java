package com.jaesay.designpatterns._02_structural_patterns._11_flyweight._02_after;

// 자주 바뀌지 않는 부분을 분리 (불변 객체)
public final class Font {
    private final String fontFamily;
    private final int fontSize;

    public Font(String fontFamily, int fontSize) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }
}
