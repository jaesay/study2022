package com.jaesay.designpatterns._01_creational_patterns._04_builder._02_after;

import com.jaesay.designpatterns._01_creational_patterns._04_builder._01_before.TourPlan;

public class App {

    public static void main(String[] args) {
        TourDirector tourDirector = new TourDirector(new DefaultTourBuilder());
        TourPlan tourPlan = tourDirector.cancunTrip();
        TourPlan tourPlan1 = tourDirector.longBeachTrip();
        System.out.println("tourPlan = " + tourPlan);
        System.out.println("tourPlan1 = " + tourPlan1);

        TourDirector tourDirector1 = new TourDirector(new TourPlanBuilder2());
        TourPlan tourPlan2 = tourDirector1.cancunTrip();
        System.out.println("tourPlan2 = " + tourPlan2);
    }
}
