package me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier;

import me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier._02_after.Billing;
import me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier._02_after.Customer;
import me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier._02_after.EmailGateway;
import me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier._02_after.Invoice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillingTest {

    @Test
    void totalOutstanding() {
        Billing billing = new Billing(new Customer("keesun", List.of(new Invoice(20), new Invoice(30))),
                new EmailGateway());
        assertEquals(50d, billing.getTotalOutstanding());

        billing.sendBill();
    }

}