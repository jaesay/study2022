package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform._01_before;

import java.time.Month;
import java.time.Year;

public record Reading(String customer, double quantity, Month month, Year year) {
}
