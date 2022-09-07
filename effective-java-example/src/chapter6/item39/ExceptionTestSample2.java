package chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class ExceptionTestSample2 {

    @ExceptionTest2({IndexOutOfBoundsException.class, NullPointerException.class})
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
}
