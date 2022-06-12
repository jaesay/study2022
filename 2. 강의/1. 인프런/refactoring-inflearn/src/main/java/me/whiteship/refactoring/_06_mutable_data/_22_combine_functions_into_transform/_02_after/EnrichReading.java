package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform._02_after;

/**
 * 자주 사용하는 필드 (baseCharge, taxableCharge)를 가지고 있음
 */
public record EnrichReading(Reading reading, double baseCharge, double taxableCharge) {
}
