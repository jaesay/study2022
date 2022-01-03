package com.ddd.order.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Orderer {

    @Column(name = "orderer_id")
    private Long memberId;

    @Column(name = "orderer_name")
    private String name;

    /* Constructor */
    protected Orderer() {
    }

    public Orderer(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    /* Getter, Setter */
    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    /* Override Method */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderer orderer = (Orderer) o;
        return Objects.equals(memberId, orderer.memberId) &&
                Objects.equals(name, orderer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name);
    }
}
