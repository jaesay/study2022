package chapter6.item34;

// 고차원의 추상 개념 하나를 완벽히 표현
public enum Planet {
    // 열거 타입의 상수 각각을 특정 데이터와 연결지으려면 생성자에서 데이터를 받아 인스턴스 필드에 저장하면 된다.
    MERCURY(3.302e+23, 2.439e6), // => MERCURY instance 생성
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    private final double mass; // 질량(단위: 킬로그램)
    private final double radius; // 반지름(단위: 미터)
    private final double surfaceGravity; // 표면중력(단위: m / S^2)

    private static final double G = 6.67300E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double getSurfaceGravity() {
        return surfaceGravity;
    }

    // 질량-에너지 등가원리? E = mc^2
    // 대상 객체의 질량을 입력받아 그 객체가 행성 표면에 있을 때의 무게를 반환한다.
    public double surfaceWeight(double mass) {
        return mass * surfaceGravity; // F = ma
    }
}