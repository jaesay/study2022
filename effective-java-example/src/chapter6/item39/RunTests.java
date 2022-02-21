package chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("chapter6.item39.Sample");

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;

                try {
                    m.invoke(null);
                    passed++;
                } catch (IllegalAccessException e) {
                    System.out.println(m + " 실패: " + e.getCause());
                } catch (Exception e) {
                    System.out.println("잘못 사용한 @Test: " + m);
                }
            }
        }

        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }
}
