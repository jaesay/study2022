package chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunExceptionTests3 {

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("chapter6.item39.ExceptionTestSample3");

        for (Method m : testClass.getDeclaredMethods()) {
            // ExceptionTest3가 한개만 있을 때 || 여러개 일떄
            if (m.isAnnotationPresent(ExceptionTest3.class) || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;

                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외 던지지 않음%n", m);
                } catch (InvocationTargetException e) {
                    Throwable throwable = e.getCause();
                    int oldPassed = passed;
                    ExceptionTest3[] exceptionTest3s = m.getAnnotationsByType(ExceptionTest3.class);

                    for (ExceptionTest3 exceptionTest3 : exceptionTest3s) {
                        if (exceptionTest3.value().isInstance(throwable)) {
                            passed++;
                            break;
                        }
                    }

                    if (passed == oldPassed) {
                        System.out.printf("테스트 %s 실패: %s%n", m, throwable);
                    }
                } catch (Exception e) {
                    System.out.println("잘못 사용한 @ExceptionTest3: " + m);
                }
            }
        }

        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }
}
