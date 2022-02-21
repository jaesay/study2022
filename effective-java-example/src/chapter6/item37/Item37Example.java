package chapter6.item37;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

// ordinal 인덱싱 대신 EnumMap 사용
public class Item37Example {

    public static void main(String[] args) {
//        new Item37Example().code37_1();

//        new Item37Example().code37_2();

//        new Item37Example().code37_3();

//        new Item37Example().code37_4();

        Phase.Transition transition = Phase.Transition.from(Phase.SOLID, Phase.LIQUID);
        System.out.println("transition = " + transition);
    }

    void code37_4() {
        List<Plant> garden = createGarden();
        // EnumMap을 사용하여 최적화
        // groupingBy 매개변수 3개짜리 사용하면 mapFactory(2번쨰 매개변수)에 원하는 맵 구현체를 인수로 전달
        // stream을 사용할 경우 값이 없는 이넘은 만들지 않음
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = garden.stream()
                .collect(groupingBy(plant -> plant.lifeCycle, () -> new EnumMap<>(Plant.LifeCycle.class), toSet()));
        System.out.println("plantsByLifeCycle = " + plantsByLifeCycle);
    }

    void code37_3() {
        List<Plant> garden = createGarden();
        // EnumMap이 아닌 고유한 맵구현체를 사용했기 때문에 공간과 성능 이점이 사라진다는 문제가 있다.
        Map<Plant.LifeCycle, List<Plant>> plantsByLifeCycle = garden.stream().collect(groupingBy(p -> p.lifeCycle));
        System.out.println("plantsByLifeCycle = " + plantsByLifeCycle);
    }

    // 열거 타입을 키로 사용하도록 설계한 EnumMap을 사용하자
    void code37_2() {
        List<Plant> garden = createGarden();
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);

        for (Plant.LifeCycle lifeCycle : Plant.LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle, new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }

        System.out.println("plantsByLifeCycle = " + plantsByLifeCycle);
    }

    // ordinal()을 배열 인덱스로 사용
    // 당연히 단점 한가득..따라하지말것
    void code37_1() {
        List<Plant> garden = createGarden();
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }

        for (Plant p : garden) {
            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
        }

        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
        }

    }

    private List<Plant> createGarden() {
        return List.of(
                new Plant("식물1", Plant.LifeCycle.ANNUAL),
                new Plant("식물2", Plant.LifeCycle.BIENNIAL),
                new Plant("식물3", Plant.LifeCycle.BIENNIAL),
                new Plant("식물4", Plant.LifeCycle.PERENNIAL),
                new Plant("식물5", Plant.LifeCycle.PERENNIAL),
                new Plant("식물6", Plant.LifeCycle.PERENNIAL),
                new Plant("식물7", Plant.LifeCycle.PERENNIAL)
        );
    }
}
