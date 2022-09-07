package chapter7.item46;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Item46Example {
    public static void main(String[] args) throws FileNotFoundException {

        String source = "Abc,abc,def,def,def,ghi,jkl";
        // 스트림 패러다임을 이해하지 못한 채 API만 사용
        // 외부 상태(freq)를 수정하는 람다를 실행
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner(source).useDelimiter(",").tokens()) {
            words.forEach(word -> freq.merge(word.toLowerCase(), 1L, Long::sum));
        }

        System.out.println(freq);

        // 변환 단계는 pure function
        Map<String, Long> freq2;
        try (Stream<String> words = new Scanner(source).useDelimiter(",").tokens()) {
            freq2 = words.collect(groupingBy(String::toLowerCase, counting()));
        }

        System.out.println(freq2);

        List<String> topTen = freq2.keySet().stream()
                .sorted(comparing(freq2::get).reversed())
                .limit(10)
                .collect(toList());

        System.out.println("topTen = " + topTen);

        // 각 키와 해당 키의 특정 원소를 연관 짓는 맵을 생성하는 수집기
        Artist artist = new Artist("김아티스트");
        Artist artist2 = new Artist("이아티스트");

        List<Album> albums = List.of(
                new Album(artist, 1),
                new Album(artist, 2),
                new Album(artist, 4),
                new Album(artist, 3),
                new Album(artist2, 5),
                new Album(artist2, 6),
                new Album(artist2, 8),
                new Album(artist2, 7)
        );

        // 인수 3개를 받는 toMap은 어떤 키와 그 키에 연관된 원소들 중 하나를 골라 연관 짓는 맵을 만들때 유리하다.
        // 앨범 스트림을 맵으로 바꾸는데, 이 맵은 각 음악가와 그 응악가의 베스트 앨범을 짝지은 것이다.
        Map<Artist, Album> topHits = albums.stream().collect(toMap(Album::getArtist, album -> album, BinaryOperator.maxBy(comparing(Album::getSales))));
        System.out.println("topHits = " + topHits);
        // 충돌이 나면 마지막 값을 취하는(last-write-wins) 수집기를 만들 때도 유용하다.
        Map<Artist, Album> lastAlbums = albums.stream().collect(toMap(Album::getArtist, album -> album, (oldAlbum, newAlbum) -> newAlbum));
        System.out.println("lastAlbums = " + lastAlbums);

        List<Book> books = List.of(
                new Book("b The Fellowship of the Ring", 1954, "0395489318"),
                new Book("a The Two Towers", 1954, "0345339711"),
                new Book("c The Return of the King", 1955, "0618129111")
        );

        TreeMap<String, Book> bookTreeMap = books.stream().collect(toMap(Book::getName, book -> book, (o1, o2) -> o1, TreeMap::new));
        System.out.println("bookTreeMap = " + bookTreeMap);

        // 입력으로 분류 함수(classifier)를 받고 출력으로는 원소들을 카테고리별로 모아 놓은 맵을 담은 수집
        Map<String, List<String>> groupingBy = Stream.of(source.split(",")).collect(groupingBy(word -> alphabetize(word)));
        System.out.println("groupingBy = " + groupingBy);

        Map<String, Long> groupingBy2 = Stream.of(source.split(",")).collect(groupingBy(String::toLowerCase, counting()));
        System.out.println("groupingBy2 = " + groupingBy2);

        // joining
        String joining1 = Stream.of(source.split(",")).collect(joining(","));
        System.out.println("joining1 = " + joining1);
        String joining2 = Stream.of(source.split(",")).collect(joining(",", "[", "]"));
        System.out.println("joining2 = " + joining2);

        // toSet
        Set<String> toSet = Stream.of(source.split(",")).map(String::toUpperCase).collect(toSet());
        System.out.println("toSet = " + toSet);
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
