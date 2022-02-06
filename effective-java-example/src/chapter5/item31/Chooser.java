package chapter5.item31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser<T> {
    private final List<T> choiceList;

    // choices 컬렉션은 T 타입을 생산하기만 하니 extends 적용
    public Chooser(Collection<? extends T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }
}
