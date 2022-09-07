package com.jaesay.designpatterns._01_creational_patterns._04_builder._02_after;

import com.jaesay.designpatterns._01_creational_patterns._04_builder._01_before.TourPlan;

import java.time.LocalDate;

public class TourPlanBuilder2 implements TourPlanBuilder {
    private TourPlan tourPlan;

    public TourPlanBuilder2 newInstance() {
        this.tourPlan = new TourPlan();
        return this;
    }

    @Override
    public TourPlanBuilder nightsAndDays(int nights, int days) {
        this.tourPlan.setNights(nights);
        this.tourPlan.setDays(days);
        return this;
    }

    @Override
    public TourPlanBuilder title(String title) {
        this.tourPlan.setTitle(title);
        return this;
    }

    @Override
    public TourPlanBuilder startDate(LocalDate localDate) {
        this.tourPlan.setStartDate(localDate);
        return this;
    }

    @Override
    public TourPlanBuilder whereToStay(String whereToStay) {
        this.tourPlan.setWhereToStay(whereToStay);
        return this;
    }

    @Override
    public TourPlanBuilder addPlan(int day, String plan) {
        this.tourPlan.addPlan(day, plan);
        return this;
    }

    @Override
    public TourPlan getPlan() {
        return this.tourPlan;
    }
}
