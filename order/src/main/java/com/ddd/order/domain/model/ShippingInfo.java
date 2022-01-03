package com.ddd.order.domain.model;

import javax.persistence.*;

@Embeddable
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
    protected ShippingInfo() {
    }

    public ShippingInfo(Address address, String message, Receiver receiver) {
        this.address = address;
        this.message = message;
        this.receiver = receiver;
    }

    /* Getter */
    public Address getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

    public Receiver getReceiver() {
        return receiver;
    }
}
