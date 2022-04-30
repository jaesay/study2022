package com.ddd.domainevent;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter @ToString
public class Aggregate2 {
    @Transient
    private final Collection<DomainEvent> domainEvents;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* Constructor */
    protected Aggregate2() {
        domainEvents = new ArrayList<>();
    }

    /* Static Method Factory */
    public static Aggregate2 create() {
        return new Aggregate2();
    }

    /* business logic */
    public void domainOperation() {
        // some business logic
        domainEvents.add(new DomainEvent());
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        domainEvents.clear();
    }


    @DomainEvents
    public Collection<DomainEvent> events() {
        return domainEvents;
    }
}
