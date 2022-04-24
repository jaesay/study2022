package chapter11.item79;

import chapter8.item50.ForwardingSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet2<E> extends ForwardingSet<E> {

    public ObservableSet2(Set<E> s) {
        super(s);
    }

    private final List<SetObserver2<E>> observers = new CopyOnWriteArrayList<>();

    public void addObserver(SetObserver2<E> observer) {
        observers.add(observer);
    }

    public boolean removeObserver(SetObserver2<E> observer) {
        return observers.remove(observer);
    }

    private void notifyElementAdded(E element) {
        for (SetObserver2<E> observer : observers) {
            observer.added(this, element);
        }
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) {
            notifyElementAdded(e);
        }

        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            result |= add(e);
        }

        return result;
    }
}
