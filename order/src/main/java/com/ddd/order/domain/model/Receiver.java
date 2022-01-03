package com.ddd.order.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Receiver {
    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_phone")
    private String phone;

    /* Constructor */
    protected Receiver() {
    }

    public Receiver(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    /* Getter, Setter */
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    /* Override Method */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receiver receiver = (Receiver) o;
        return Objects.equals(name, receiver.name) &&
                Objects.equals(phone, receiver.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
