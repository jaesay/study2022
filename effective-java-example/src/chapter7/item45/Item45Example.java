package chapter7.item45;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

public class Item45Example {
    public static void main(String[] args) {
        // char 값들을 처리할 떄는 스트림을 삼가하는 편이 낫다.
        // IntStream 반환, 명시적 형변환
        "Hello world".chars().forEach(x -> System.out.print((char) x));

        // 메르센 소수
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
//                .forEach(System.out::println);
                .forEach(mp -> System.out.println(mp.bitLength() + ": " + mp)); // 앞 단계의 값이 필요할 때 매핑을 거꾸로 수행..

        // 데카르트 곱
        newDeck2().forEach(System.out::println);
    }

    private static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    private static List<Card> newDeck() {
        List<Card> result = new ArrayList<>();

        for (Card.SUIT suit : Card.SUIT.values()) {
            for (Card.RANK rank : Card.RANK.values()) {
                result.add(new Card(suit, rank));
            }
        }

        return result;
    }

    private static List<Card> newDeck2() {
        return Stream.of(Card.SUIT.values())
                .flatMap(suit -> Stream.of(Card.RANK.values())
                .map(rank -> new Card(suit, rank)))
                .toList();
    }
}
