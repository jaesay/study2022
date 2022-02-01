package com.jaesay.chapter4;

import java.time.Duration;

public class Progress {
    private Duration studyDuration;
    private boolean finished;

    public Progress(Duration studyDuration, boolean finished) {
        this.studyDuration = studyDuration;
        this.finished = finished;
    }

    public Duration getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(Duration studyDuration) {
        this.studyDuration = studyDuration;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "studyDuration=" + studyDuration +
                ", finished=" + finished +
                '}';
    }
}
