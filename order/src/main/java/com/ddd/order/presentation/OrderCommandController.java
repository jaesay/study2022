package com.ddd.order.presentation;

import com.ddd.order.application.command.PlaceOrderCommand;
import com.ddd.order.application.command.PlaceOrderCommandResult;
import com.ddd.order.application.command.PlaceOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private final PlaceOrderService placeOrderService;

    public OrderCommandController(PlaceOrderService placeOrderService) {
        this.placeOrderService = placeOrderService;
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
    }
}
