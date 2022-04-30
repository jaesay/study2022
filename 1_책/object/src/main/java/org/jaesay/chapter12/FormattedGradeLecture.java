package org.jaesay.chapter12;

import java.util.List;

public class FormattedGradeLecture extends GradeLecture {
    public FormattedGradeLecture(String title, int pass, List<Integer> scores, List<Grade> grades) {
        super(title, pass, scores, grades);
    }

    public String formatAverage() {
        return String.format("Avg: %1.1f", super.average());
    }
}
