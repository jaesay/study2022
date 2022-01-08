package com.ddd.domainevent;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
public class Aggregate {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* Constructor */
    protected Aggregate() {}

    /* Static Factory Method */
    public static Aggregate create() {
        return new Aggregate();
    }

    /* business logic */
    public void domainOperation() {
        System.out.println("domainOperation.............");
    }

    public void domainOperation(ApplicationEventPublisher eventPublisher) {
        // some business logic
        if (eventPublisher != null) {
            eventPublisher.publishEvent(new DomainEvent());
        }
    }
}
