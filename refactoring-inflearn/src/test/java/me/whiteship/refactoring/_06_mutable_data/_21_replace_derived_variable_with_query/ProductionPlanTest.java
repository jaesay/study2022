package me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query;

import me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query._02_after.ProductionPlan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionPlanTest {

    @Test
    void production() {
        ProductionPlan productionPlan = new ProductionPlan();
        productionPlan.applyAdjustment(10);
        productionPlan.applyAdjustment(20);
        assertEquals(30, productionPlan.getProduction());
    }

}