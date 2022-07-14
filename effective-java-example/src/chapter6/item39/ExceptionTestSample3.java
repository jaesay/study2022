package chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class ExceptionTestSample3 {

    @ExceptionTest3(IndexOutOfBoundsException.class)
    @ExceptionTest3(NullPointerException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
}
