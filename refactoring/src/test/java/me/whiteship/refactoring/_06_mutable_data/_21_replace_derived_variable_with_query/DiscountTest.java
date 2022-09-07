package me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query;

import me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query._02_after.Discount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    @Test
    void discount() {
        Discount discount = new Discount(100);
//        assertEquals(100, discount.getDiscountedTotal());

        discount.setDiscount(10);
        assertEquals(90, discount.getDiscountedTotal());
    }

}