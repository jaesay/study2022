package chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunExceptionTests {
    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("chapter6.item39.ExceptionTestSample");

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;

                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외 던지지 않음%n", m);
                } catch (InvocationTargetException e) {
                    Throwable throwable = e.getCause();
                    Class<? extends Throwable> exceptionType = m.getAnnotation(ExceptionTest.class).value();
                    if (exceptionType.isInstance(throwable)) {
                        passed++;
                    } else {
                        System.out.printf("테스트 %s 실패: 기대한 예외 %s, 발생한 예외 %s%n", m, exceptionType.getName(), throwable);
                    }
                } catch (Exception e) {
                    System.out.println("잘못 사용한 @ExceptionTest: " + m);
                }
            }
        }

        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }
}
