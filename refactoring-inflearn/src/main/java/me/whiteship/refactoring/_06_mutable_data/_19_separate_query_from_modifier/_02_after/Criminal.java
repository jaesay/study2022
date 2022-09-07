package me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier._02_after;

import java.util.List;

public class Criminal {

    // 변경
    public void alertForMiscreant(List<Person> people) {
        if (!findMiscreant(people).isBlank()) {
            setOffAlarms();
        }
    }

    // 질의
    public String findMiscreant(List<Person> people) {
        for (Person p : people) {
            if (p.getName().equals("Don")) {
                return "Don";
            }

            if (p.getName().equals("John")) {
                return "John";
            }
        }

        return "";
    }

    private void setOffAlarms() {
        System.out.println("set off alarm");
    }
}
