package me.whiteship.refactoring._03_long_function._09_preserve_whole_object;

import java.util.HashMap;
import java.util.Map;

public record Participant(String username, Map<Integer, Boolean> homework) {
    public Participant(String username) {
        this(username, new HashMap<>());
    }

    public void setHomeworkDone(int index) {
        this.homework.put(index, true);
    }


    // preserve whole object 이후에는
    // 1. 이 메서드가 파라미터로 사용하고 있는 participant를 의존하고 있는게 맞는가? 괜찮은 것 같다
    // 2. 이 메서드가 위치가 여기가 맞는가? Participant(whole object) 클래스에 있는게 적절한 것 같다.
    double getRate(int totalNumberOfEvents) {
        long count = homework().values().stream()
                .filter(v -> v == true)
                .count();
        return (double) (count * 100 / totalNumberOfEvents);
    }
}
