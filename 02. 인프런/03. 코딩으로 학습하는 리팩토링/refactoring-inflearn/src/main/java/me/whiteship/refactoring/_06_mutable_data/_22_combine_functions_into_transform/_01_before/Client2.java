package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform._01_before;

import java.time.Month;
import java.time.Year;

public class Client2 {

    /**
     * 기본 요금
     */
    private double base;
    /**
     * 세금
     */
    private double taxableCharge;

    public Client2(Reading reading) {
        // 이곳 저곳 자주 사용되는 계산 코드
        this.base = baseRate(reading.month(), reading.year()) * reading.quantity();
        this.taxableCharge = Math.max(0, this.base - taxThreshold(reading.year()));
    }

    /**
     * 세금을 부과할 소비량
     * @param year
     * @return
     */
    private double taxThreshold(Year year) {
        return 5;
    }

    /**
     * 기본 소비량
     * @param month
     * @param year
     * @return
     */
    private double baseRate(Month month, Year year) {
        return 10;
    }

    public double getBase() {
        return base;
    }

    public double getTaxableCharge() {
        return taxableCharge;
    }
}
