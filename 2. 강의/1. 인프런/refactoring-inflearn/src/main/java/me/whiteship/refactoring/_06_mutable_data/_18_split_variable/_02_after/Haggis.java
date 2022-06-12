package me.whiteship.refactoring._06_mutable_data._18_split_variable._02_after;

public class Haggis {

    private double primaryForce;
    private double secondaryForce;
    private double mass;
    private int delay;

    public Haggis(double primaryForce, double secondaryForce, double mass, int delay) {
        this.primaryForce = primaryForce;
        this.secondaryForce = secondaryForce;
        this.mass = mass;
        this.delay = delay;
    }

    /**
     *
     * @param time
     * @return
     */
    public double distanceTravelled(int time) {
        double result; // 결과값을 계산하기 떄문에 같이 사용
        final double primaryAcc = primaryForce / mass; // 변경을 못하도록 하는 값을 final로 명시
        int primaryTime = Math.min(time, delay);
        result = 0.5 * primaryAcc * primaryTime * primaryTime;

        int secondaryTime = time - delay;
        if (secondaryTime > 0) {
            final double primaryVelocity = primaryAcc * delay;
            final double secondaryAcc = (primaryForce + secondaryForce) / mass;
            result += primaryVelocity * secondaryTime + 0.5 * secondaryAcc * secondaryTime + secondaryTime;
        }

        return result;
    }
}
