package chapter5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

// 배열의 타입을 E[]로 선언하여 오직 E 타입 인스턴스만 받음을 확실히 어필
// 코드 짧음
// 보통의 제네릭 클래스라면 이곳저곳 이 배열을 사용할텐데 형변환을 배열 생성 시 단 한번만 해주면 됨
// (E가 Object가 아닌 한) 배열의 런타임 타입이 컴파일타임 타입과 달라 heap pollution을 일으킨다.
// 현업에서 선호
public class Stack2<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked") // 이 경우는 비검사 배열 생성 말고는 하는 일이 없으니 생성자 전체에서 경고를 숨겨도 좋다.
    public Stack2() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
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
