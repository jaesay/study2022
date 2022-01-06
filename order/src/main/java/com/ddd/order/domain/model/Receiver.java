package com.ddd.order.domain.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@ToString
public class Receiver {
    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_phone")
    private String phone;

    /* Constructor */
    protected Receiver() {}

    /* Static Factory Method */
    public static Receiver create(String name, String phone) {
        Receiver receiver = new Receiver();
        receiver.name = name;
        receiver.phone = phone;
        return receiver;
    }

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
}
