package chapter5.item31;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {
    private final List<T> list = new ArrayList<>();

    public T get() {
        return list.get(0);
    }
    public void put( T element) {
        list.add(element);
    }
}

