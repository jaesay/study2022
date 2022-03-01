package chapter7.item46;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
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
    }
}
