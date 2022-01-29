package chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Item28_Chooser<T> {
    private final List<T> choiceList;

    public Item28_Chooser(Collection<T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }
}

//class Chooser {
//    private final Object[] choiceArray;
//
//    public Chooser(Collection choices) {
//        choiceArray = choices.toArray();
//    }
//
//    public Object choose() {
//        Random rnd = ThreadLocalRandom.current();
//        return choiceArray[rnd.nextInt(choiceArray.length)];
//    }
//}
//
//class Chooser1<T> {
//    private final T[] choiceArray;
//
//    public Chooser1(Collection<T> choices) {
//        choiceArray = choices.toArray();
//    }
//}
//
//class Chooser2<T> {
//    private final T[] choiceArray;
//
//    public Chooser2(Collection<T> choices) {
//        choiceArray = (T[]) choices.toArray(); // unchecked warnings
//    }
//
//    public T choose() {
//        Random rnd = ThreadLocalRandom.current();
//        return choiceArray[rnd.nextInt(choiceArray.length)];
//    }
//}
