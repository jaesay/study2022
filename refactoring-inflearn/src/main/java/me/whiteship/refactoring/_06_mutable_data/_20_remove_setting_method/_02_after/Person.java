package me.whiteship.refactoring._06_mutable_data._20_remove_setting_method._02_after;

public class Person {

    private String name;

    private final int id;

    public Person(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
