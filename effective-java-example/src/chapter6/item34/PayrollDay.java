package chapter6.item34;

import static chapter6.item34.PayrollDay.PayType.WEEKDAY;
import static chapter6.item34.PayrollDay.PayType.WEEKEND;

public enum PayrollDay {

    // 새로운 상수를 추가할 때 잔업수당 전략을 선택하도록 한다.
    // PayrllDay 열거 타입은 잔업수당 계산을 그 전략 열거 타입에 위임한다.
    // switch method / 각 상수별로 도우미 메소드 호출 / 평일 상수 주말 상수 분기 후 주말 상수에서 메소드 재정의에서 발생하는 문제를 피하면서
    // 열거 타입 상수끼리 코드를 공유할 수 있다.
    MONDAY(WEEKDAY), TUESDAY(WEEKDAY), WEDNESDAY(WEEKDAY), THURSDAY(WEEKDAY), FRIDAY(WEEKDAY), SATURDAY(WEEKEND), SUNDAY(WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }
    
    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    enum PayType {
        WEEKDAY {
            @Override
            int overtimePay(int minutesWorked, int payRate) {
                return minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            @Override
            int overtimePay(int minutesWorked, int payRate) {
                return minutesWorked * payRate / 2;
            }
        };
        
        private static final int MINS_PER_SHIFT = 8 * 60; // 하루 8시간
        abstract int overtimePay(int minutesWorked, int payRate);
        
        int pay(int minutesWorked, int payRate) {
            int basePay = minutesWorked * payRate;
            return basePay + overtimePay(minutesWorked, payRate);
        }
    }

    /*
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    private static final int MINS_PER_SHIFT = 8 * 60; // 하루 8시간

    // (시간당) 기본임금, 그날 일한 시간(분 단위)
    int pay(int minutesWorked, int payRate) {
        int basePay = minutesWorked * payRate;

        int overtimePay = switch (this) {
            case SATURDAY, SUNDAY -> basePay / 2; // 주말
            default -> minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
        };
        return basePay + overtimePay;
    }
    */
}
