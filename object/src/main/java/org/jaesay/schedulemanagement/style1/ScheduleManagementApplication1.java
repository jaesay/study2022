package org.jaesay.schedulemanagement.style1;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduleManagementApplication1 {

    public static void main(String[] args) {
        Event meeting = new Event("회의", LocalDateTime.of(2019, 5, 8, 10, 30), Duration.ofMinutes(30));

        RecurringSchedule schedule = new RecurringSchedule("회의", DayOfWeek.WEDNESDAY, LocalTime.of(10, 30), Duration.ofMinutes(30));

        assert meeting.isSatisfied(schedule) == true; // 명령과 쿼리의 두가지 역할을 동시에 수행 => 버그 발생
        assert meeting.isSatisfied(schedule) == false;
    }
}
