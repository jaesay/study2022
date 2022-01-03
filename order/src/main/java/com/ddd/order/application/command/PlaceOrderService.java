package com.ddd.order.application.command;

import com.ddd.order.application.command.PlaceOrderCommand.OrderProductCommand;
import com.ddd.order.application.command.PlaceOrderCommand.ShippingInfoCommand;
import com.ddd.order.domain.model.*;
import com.ddd.order.domain.service.DiscountCalculationService;
import com.ddd.order.infra.client.ClientHelper;
import com.ddd.order.infra.client.MemberDto;
import com.ddd.order.infra.client.ProductDto;
import com.ddd.order.infra.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceOrderService {

    private final OrderRepository repository;
    private final DiscountCalculationService discountCalculationService;
    private final ClientHelper clientHelper;

    public PlaceOrderService(OrderRepository repository, DiscountCalculationService discountCalculationService, ClientHelper clientHelper) {
        this.repository = repository;
        this.discountCalculationService = discountCalculationService;
        this.clientHelper = clientHelper;
    }

    @Transactional
    public PlaceOrderCommandResult placeOrder(PlaceOrderCommand command) {
        // 주문자 생성
        Orderer orderer = createOrderer(command.getMemberId());

        // 주문상품 생성
        List<OrderProduct> orderProducts = command.getOrderProductCommands().stream()
                .map(this::createOrderProduct)
                .collect(Collectors.toList());

        // 배송정보 생성
        ShippingInfo shippingInfo = createShoppingInfo(command.getShippingInfoCommand());

        // 주문 생성
        OrderEntity orderEntity = new OrderEntity(orderer, orderProducts, shippingInfo);

        // 할인 계산
        // Bounded Context로 결제(Payment)를 추가하고 결제 서비스에서 처리하는 것도 좋아보임
        // 그럴 경우 요구사항에 맞게 트랜잭션 처리가 필요
        orderEntity.calculatePaymentAmounts(discountCalculationService);

        // 주문 저장
        repository.save(orderEntity);

        return PlaceOrderCommandResult.from(orderEntity);
    }

    private Orderer createOrderer(long memberId) {
        MemberDto member = clientHelper.findMember(memberId);
        return new Orderer(member.getMemberId(), member.getName());
    }

    private OrderProduct createOrderProduct(OrderProductCommand command) {
        ProductDto product = clientHelper.findProduct(command.getProductId());
        return new OrderProduct(product.getId(), product.getPrice(), command.getQuantity());
    }

    private ShippingInfo createShoppingInfo(ShippingInfoCommand command) {
        Address address = new Address(command.getZipCode(), command.getAddress1(), command.getAddress2());
        Receiver receiver = new Receiver(command.getReceiverName(), command.getReceiverPhone());
        return new ShippingInfo(address, command.getMessage(), receiver);
    }
}
