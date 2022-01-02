package com.ddd.order.application.command;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PlaceOrderCommand {
    @NotNull
    private Long memberId;

    @Valid
    private List<OrderProductCommand> orderProductCommands;

    @Valid
    private ShippingInfoCommand shippingInfoCommand;

    /* Inner Class*/
    public static class OrderProductCommand {
        @NotNull
        private Long productId;
        @Length(min = 1)
        private int quantity;

        /* Getter */
        public Long getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
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
    }

    /* Getter */
    public Long getMemberId() {
        return memberId;
    }

    public List<OrderProductCommand> getOrderProductCommands() {
        return orderProductCommands;
    }

    public ShippingInfoCommand getShippingInfoCommand() {
        return shippingInfoCommand;
    }
}
