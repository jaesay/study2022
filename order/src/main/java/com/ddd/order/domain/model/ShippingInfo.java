package com.ddd.order.domain.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Getter
@ToString
public class ShippingInfo {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zip_code")),
            @AttributeOverride(name = "address1", column = @Column(name = "shipping_addr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "shipping_addr2"))
    })
    private Address address;

    @Column(name = "shipping_message")
    private String message;

    @Embedded
    private Receiver receiver;

    /* Constructor */
    protected ShippingInfo() {}

    /* Static Factory Method*/
    public static ShippingInfo create(Address address, String message, Receiver receiver) {
        ShippingInfo shippingInfo = new ShippingInfo();
        shippingInfo.address = address;
        shippingInfo.message = message;
        shippingInfo.receiver = receiver;
        return shippingInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingInfo that = (ShippingInfo) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(message, that.message) &&
                Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, message, receiver);
    }
}
