package com.jaesay.designpatterns._01_creational_patterns._04_builder._02_after;

import com.jaesay.designpatterns._01_creational_patterns._04_builder._01_before.TourPlan;

import java.time.LocalDate;

public interface TourPlanBuilder {
    TourPlanBuilder newInstance();
    TourPlanBuilder nightsAndDays(int nights, int days);
    TourPlanBuilder title(String title);
    TourPlanBuilder startDate(LocalDate localDate);
    TourPlanBuilder whereToStay(String whereToStay);
    TourPlanBuilder addPlan(int day, String plan);
    TourPlan getPlan();
}
