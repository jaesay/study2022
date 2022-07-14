package me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query._01_before;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan {

    private double production; // 파생 변수
    private List<Double> adjustments = new ArrayList<>();

    public void applyAdjustment(double adjustment) {
        this.adjustments.add(adjustment);
        this.production += adjustment; // 조정 값(adjustments)을 적용하는 과정에서 직접 관련이 없는 누적값(production) 까지 갱신했다.
    }

    public double getProduction() {
        return this.production;
    }
}
