package chapter8.item50;

import java.util.Date;

public class Period3 {
    private final Date start;
    private final Date end;

    public Period3(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException();
        }
    }

    public Date getStart() {
        return new Date(this.start.getTime());
    }

    public Date getEnd() {
        return new Date(this.end.getTime());
    }
}
