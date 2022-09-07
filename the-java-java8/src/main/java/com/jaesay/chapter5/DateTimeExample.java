package com.jaesay.chapter5;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeExample {
    public static void main(String[] args) {
        // java 8 이전 날짜 타입은?
        // Thread safe X, mutable, unclear, ...

        // java 8 이후는?
        // joda time library를 JSR-310으로
        // 기계용 시간과 인류용 시간을 제공
        // 1. 기계용 시간 => epock
        Instant now = Instant.now(); // 현재 UTC (GMT)를 리턴한다.
        System.out.println(now);
        System.out.println(now.atZone(ZoneId.of("UTC"))); // UTC (GMT)

        // 2. 인류용 시간
        // 현재 시스템 Zone에 해당하는(로컬) 일시
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        System.out.println(localDateTimeNow);

        // 특정 Zone의 특정 일시
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        System.out.println(zonedDateTimeNow);

        // zone <=> local <=> instant
        System.out.println(now.atZone(ZoneId.systemDefault()));
        System.out.println(zonedDateTimeNow.toLocalDateTime());
        System.out.println(localDateTimeNow.atZone(ZoneId.of("Asia/Seoul")));

        // 기간을 표현하는 방법 => period, duration
        // period: 인류용 기간 비교
        LocalDate today = LocalDate.now();
        LocalDate birthDay = LocalDate.of(1991, Month.JANUARY, 11);
        Period between = Period.between(today, birthDay);
        Period until = today.until(birthDay);
        System.out.println(between.get(ChronoUnit.DAYS));
        System.out.println(until.getDays());

        // duration: 기계용 시간 비교
        Instant instantNow = Instant.now();
        Instant plus = instantNow.plus(10, ChronoUnit.SECONDS);
        Duration between1 = Duration.between(instantNow, plus);
        System.out.println(between1.getSeconds());

        // formatting & parsing
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy"); // 미리 정의된 포멧터가 있다면 사용
        LocalDate date = LocalDate.parse("01/11/1991", formatter); // parsing
        System.out.println(date);
        System.out.println(date.format(formatter));

        // Legacy API 전환 가능
        ZoneId newZoneAPI = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone legacyZoneAPI = TimeZone.getTimeZone(newZoneAPI);

        Instant newInstant = new Date().toInstant();
        Date legacyInstant = Date.from(newInstant);
    }

}
