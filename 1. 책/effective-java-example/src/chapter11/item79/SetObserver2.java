package chapter11.item79;

@FunctionalInterface
public interface SetObserver2<E> {
    void added(ObservableSet2<E> set, E element);
}
