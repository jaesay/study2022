package com.jaesay.designpatterns._01_creational_patterns._02_factory_method._03_java;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 자바에서 팩토리 메소드 패턴 예
 */
public class CalendarExample {

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getClass());
        System.out.println(Calendar.getInstance(Locale.forLanguageTag("th-TH-x-lvariant-TH")).getClass());
        System.out.println(Calendar.getInstance(Locale.forLanguageTag("ja-JP-x-lvariant-JP")).getClass());

        System.out.println(NumberFormat.getInstance().getClass());
        // ...
    }
}
