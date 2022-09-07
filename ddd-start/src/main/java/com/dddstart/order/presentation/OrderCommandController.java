package com.dddstart.order.presentation;

import com.ddd.order.command.application.*;
import com.dddstart.order.command.application.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderCommandController {

    private final PlaceOrderService placeOrderService;
    private final StartShippingService startShippingService;
    private final CancelOrderService cancelOrderService;

    public OrderCommandController(PlaceOrderService placeOrderService, StartShippingService startShippingService, CancelOrderService cancelOrderService) {
        this.placeOrderService = placeOrderService;
        this.startShippingService = startShippingService;
        this.cancelOrderService = cancelOrderService;
    }

    @PostMapping
    public ResponseEntity<PlaceOrderCommandResult> placeOrder(@Valid @RequestBody PlaceOrderCommand placeOrderCommand, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, getErrorMessage(errors));
        }

        PlaceOrderCommandResult result = placeOrderService.placeOrder(placeOrderCommand);
        return ResponseEntity.ok(result);
//        return ResponseEntity.created(URI.create("/orders/" + result.getOrderId())).body(result); 이런식으로 해도 될 것 같음
    }

    // request body 없이 @PatchMapping("/{orderId}/versions/{versionId}/start-shipping 이런식으로 해도 될 것 같음
    @PostMapping("/start-shipping")
    public ResponseEntity<StartShippingCommandResult> startShipping(@Valid @RequestBody StartShippingCommand command, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, getErrorMessage(errors));
        }

        StartShippingCommandResult result = startShippingService.startShipping(command);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cancel")
    public ResponseEntity<CancelOrderCommandResult> cancel(@Valid @RequestBody CancelOrderCommand command, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, getErrorMessage(errors));
        }

        CancelOrderCommandResult result = cancelOrderService.cancel(command);
        return ResponseEntity.ok(result);
    }

    private String getErrorMessage(Errors errors) {
        return errors.getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.joining(", "));
    }
}
