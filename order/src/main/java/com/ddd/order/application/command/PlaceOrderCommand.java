package com.ddd.order.application.command;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PlaceOrderCommand {
    @NotNull
    private long memberId;

    @Valid
    private List<OrderProductCommand> orderProductCommands;

    @Valid
    private ShippingInfoCommand shippingInfoCommand;

    public static class OrderProductCommand {
        @NotNull
        private long productId;
        @Length(min = 1)
        private int quantity;

        /* Getter */
        public long getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
        }

        /* Setter */
        public void setProductId(long productId) {
            this.productId = productId;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static class ShippingInfoCommand {
        @NotBlank
        @Length(min = 2, max = 10)
        private String zipCode;

        @NotBlank
        @Length(min = 2, max = 150)
        private String address1;

        @NotBlank
        @Length(min = 2, max = 150)
        private String address2;

        @NotBlank
        @Length(max = 100)
        private String message;

        @NotBlank
        @Length(min = 2, max = 30)
        private String receiverName;

        @NotBlank
        @Length(min = 5, max = 15)
        private String receiverPhone;

        /* Getter */
        public String getZipCode() {
            return zipCode;
        }

        public String getAddress1() {
            return address1;
        }

        public String getAddress2() {
            return address2;
        }

        public String getMessage() {
            return message;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public String getReceiverPhone() {
            return receiverPhone;
        }

        /* Setter */
        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.receiverPhone = receiverPhone;
        }
    }

    /* Getter */
    public long getMemberId() {
        return memberId;
    }

    public List<OrderProductCommand> getOrderProductCommands() {
        return orderProductCommands;
    }

    public ShippingInfoCommand getShippingInfoCommand() {
        return shippingInfoCommand;
    }

    /* Setter */
    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setOrderProductCommands(List<OrderProductCommand> orderProductCommands) {
        this.orderProductCommands = orderProductCommands;
    }

    public void setShippingInfoCommand(ShippingInfoCommand shippingInfoCommand) {
        this.shippingInfoCommand = shippingInfoCommand;
    }
}
