package com.dddstart.order.command.application;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @ToString @Builder
public class PlaceOrderCommand {
    @Min(1)
    private long memberId;

    @Valid
    private List<OrderProductCommand> orderProductCommands;

    @Valid
    private ShippingInfoCommand shippingInfoCommand;

    // 롬복 @Bulder 시 모든 필드를 argument로 가진 constructor가 필요하여 private으로 생성
    private PlaceOrderCommand(long memberId, List<OrderProductCommand> orderProductCommands, ShippingInfoCommand shippingInfoCommand) { this.memberId = memberId;this.orderProductCommands = orderProductCommands;this.shippingInfoCommand = shippingInfoCommand; }

    @Getter @ToString @Builder
    public static class OrderProductCommand {
        @Min(1)
        private long productId;
        @Min(1)
        private int quantity;

        private OrderProductCommand(long productId, int quantity) { this.productId = productId;this.quantity = quantity; }
    }

    @Getter @ToString @Builder
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

        private ShippingInfoCommand(String zipCode, String address1, String address2, String message, String receiverName, String receiverPhone) { this.zipCode = zipCode;this.address1 = address1;this.address2 = address2;this.message = message;this.receiverName = receiverName;this.receiverPhone = receiverPhone; }
    }
}
