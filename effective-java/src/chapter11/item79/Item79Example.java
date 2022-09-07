package chapter11.item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Item79Example {

    public static void main(String[] args) {
//        example1();
        example2();
//        example3();
    }

    private static void example1() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver((s, e) -> System.out.println(e));

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    private static void example2() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        // 자바 언어의 락은 재진입(reentrant)을 허용하므로 교착상태에 빠지지는 않는다. => 예외가 발생할 때 불변식이 깨졌다면 문제가 발생..아마 실패원자적(failure-atomic)을 얘기하는 것 같다.
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    set.removeObserver(this);
                }
            }
        });

        set.add(23);

//        for (int i = 0; i < 100; i++) {
//            set.add(i);
//        }
    }

    private static void example3() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();

                    try {
                        executorService.submit(() -> set.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);
                    } finally {
                        executorService.shutdown();
                    }

                }
            }
        });

        // notifyElementAdded lock을 얻었는데 내부 메소드(removeObserver)에서 또 락으 얻으려고 해서 deadlock
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
