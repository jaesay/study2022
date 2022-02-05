package chapter5.Item31;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked") // 이 경우는 비검사 배열 생성 말고는 하는 일이 없으니 생성자 전체에서 경고를 숨겨도 좋다.
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    // PECS: producer-extends, consumer-super
    public void pushAll_v1(Iterable<E> src) {
        for (E e: src) push(e);
    }

    public void pushAll_v2(Iterable<? extends E> src) {
        for (E e: src) push(e);
    }

    public void popAll_v1(Collection<E> dst) {
        while (!isEmpty()) dst.add(pop());
    }

    public void popAll_v2(Collection<? super E> dst) {
        while (!isEmpty()) dst.add(pop());
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) throw new EmptyStackException();
        E result = elements[--size];
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
