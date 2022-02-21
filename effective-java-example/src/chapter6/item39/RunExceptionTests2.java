package chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunExceptionTests2 {

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("chapter6.item39.ExceptionTestSample2");

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest2.class)) {
                tests++;

                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외 던지지 않음%n", m);
                } catch (InvocationTargetException e) {
                    Throwable throwable = e.getCause();
                    int oldPassed = passed;
                    Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionTest2.class).value();

                    for (Class<? extends Throwable> exceptionTest2 : excTypes) {
                        if (exceptionTest2.isInstance(throwable)) {
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
