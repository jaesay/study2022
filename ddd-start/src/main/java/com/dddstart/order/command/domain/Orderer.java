package com.dddstart.order.command.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@ToString
public class Orderer {

    @Column(name = "orderer_id")
    private Long memberId;

    @Column(name = "orderer_name")
    private String name;

    protected Orderer() {}

    public static Orderer create(Long memberId, String name) {
        Orderer orderer = new Orderer();
        orderer.memberId = memberId;
        orderer.name = name;
        return orderer;
    }

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
