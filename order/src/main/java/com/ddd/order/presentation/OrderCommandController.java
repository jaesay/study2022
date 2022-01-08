package com.ddd.order.presentation;

import com.ddd.order.application.command.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderCommandController {

    private final PlaceOrderService placeOrderService;
    private final StartShippingService startShippingService;

    public OrderCommandController(PlaceOrderService placeOrderService, StartShippingService startShippingService) {
        this.placeOrderService = placeOrderService;
        this.startShippingService = startShippingService;
    }

    @PostMapping
    public ResponseEntity<PlaceOrderCommandResult> placeOrder(@Valid @RequestBody PlaceOrderCommand placeOrderCommand, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors()
                    .stream().map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException(errorMessage);
        }

        PlaceOrderCommandResult result = placeOrderService.placeOrder(placeOrderCommand);
        return ResponseEntity.ok(result);
//        return ResponseEntity.created(URI.create("/orders/" + result.getOrderId())).body(result); 이런식으로 해도 될 것 같음
    }

    // request body 없이 @PatchMapping("/{orderId}/versions/{versionId}/start-shipping 이런식으로 해도 될 것 같음
    @PostMapping("/start-shipping")
    public ResponseEntity<StartShippingCommandResult> startShipping(@Valid @RequestBody StartShippingCommand command, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
            throw new IllegalArgumentException(errorMessage);
        }

        StartShippingCommandResult result = startShippingService.startShipping(command);
        return ResponseEntity.ok(result);
    }
}
