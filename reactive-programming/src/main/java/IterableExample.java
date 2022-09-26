import java.util.Iterator;

public class IterableExample {
  public static void main(String[] args) {
    Iterable<Integer> iterable = () -> new Iterator<>() {
      int i = 0;
      static final int MAX = 10;

      @Override
      public boolean hasNext() {
        return i < MAX;
      }

      @Override
      public Integer next() {
        return ++i;
      }
    };

    for (Integer i : iterable) {
      System.out.println(i);
    }

    for (Iterator<Integer> iterator = iterable.iterator(); iterator.hasNext(); ) {
      System.out.println(iterator.next()); // pull
    }
  }
}
