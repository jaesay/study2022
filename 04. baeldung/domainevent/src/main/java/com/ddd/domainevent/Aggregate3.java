package com.ddd.domainevent;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
public class Aggregate3 extends AbstractAggregateRoot<Aggregate3> {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    /* Constructor */
    protected Aggregate3() {}

    /* Static Factory Method */
    public static Aggregate3 create(String name) {
        Aggregate3 aggregate3 = new Aggregate3();
        aggregate3.name = name;
        return aggregate3;
    }

    /* business logic */
    public void domainOperation() {
        // some business logic
        registerEvent(new DomainEvent());
    }

    public void changeName(String name) {
        this.name = name;
    }
}
