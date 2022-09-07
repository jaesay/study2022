package me.whiteship.refactoring._07_divergent_change._26_extract_class;

public class Person {

    private final TelephoneNumber telephoneNumber;
    private String name;

    public Person(TelephoneNumber telephoneNumber, String name) {
        this.telephoneNumber = telephoneNumber;
        this.name = name;
    }

    public String telephoneNumber() {
        return this.telephoneNumber.toString();
    }

    public TelephoneNumber getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
