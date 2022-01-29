package chapter5;

import java.util.Arrays;
import java.util.EmptyStackException;

// 원소를 읽을 때마다 형변환해야 됨
// heap pollution이 맘에 걸리는 프로그래머는 두번째 방식을 고수
public class Item29_Stack3<E> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Item29_Stack3() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) throw new EmptyStackException();
        @SuppressWarnings("unchecked") E result = (E) elements[--size];
        elements[size] = null; // 다 쓴 참조 객체
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
