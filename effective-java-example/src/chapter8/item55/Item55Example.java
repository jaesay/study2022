package chapter8.item55;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Item55Example {
    public static void main(String[] args) {
        List<Integer> c = List.of(1, 2, 3);
        System.out.println(max(c));
        System.out.println(c.stream().max(Comparator.naturalOrder()));

        ProcessHandle ph = ProcessHandle.current();
        Optional<ProcessHandle> parentProcess = ph.parent();

        System.out.println(parentProcess.map(h -> String.valueOf(h.pid())).orElse("N/A"));

        // java 8
        Stream<Optional<String>> optionalStream = Stream.of(Optional.of("a"), Optional.of("b"), Optional.of("c"), Optional.of("d"));
        List<String> collect = optionalStream.filter(Optional::isEmpty).map(Optional::get).toList();


        // java 9 ~
        Stream<Optional<String>> optionalStream2 = Stream.of(Optional.of("a"), Optional.of("b"), Optional.of("c"), Optional.of("d"));
        Stream<String> stringStream = optionalStream2.flatMap(Optional::stream);

        OptionalInt optionalInt = OptionalInt.of(1);
    }

    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
        if (c.isEmpty()) return Optional.empty();

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);
    }
}
