package org.jaesay.chapter14.ex2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Call {
    private DateTimeInterval interval;

    public Call(LocalDateTime from, LocalDateTime to) {
        this.interval = DateTimeInterval.of(from, to);
    }

    public Duration getDuration() {
        return this.interval.duration();
    }

    public DateTimeInterval getInterval() {
        return this.interval;
    }

    public LocalDateTime getFrom() {
        return this.interval.getFrom();
    }

    public LocalDateTime getTo() {
        return this.interval.getTo();
    }

    public List<DateTimeInterval> splitByDay() {
        return interval.splitByDay();
    }
}
