package com.ddd.order.application.command;

import com.ddd.order.application.command.PlaceOrderCommand.OrderProductCommand;
import com.ddd.order.application.command.PlaceOrderCommand.ShippingInfoCommand;
import com.ddd.order.domain.model.*;
import com.ddd.order.domain.service.DiscountCalculationService;
import com.ddd.order.infra.client.MemberClient;
import com.ddd.order.infra.client.MemberDto;
import com.ddd.order.infra.client.ProductClient;
import com.ddd.order.infra.client.ProductDto;
import com.ddd.order.infra.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceOrderService {

    private final OrderRepository repository;
    private final MemberClient memberClient;
    private final ProductClient productClient;
    private final DiscountCalculationService discountCalculationService;

    public PlaceOrderService(OrderRepository repository, MemberClient memberClient, ProductClient productClient, DiscountCalculationService discountCalculationService) {
        this.repository = repository;
        this.memberClient = memberClient;
        this.productClient = productClient;
        this.discountCalculationService = discountCalculationService;
    }

    @Transactional
    public PlaceOrderCommandResult placeOrder(PlaceOrderCommand command) {
        // 주문자 생성
        MemberDto member = findMember(command);
        Orderer orderer = new Orderer(member.getMemberId(), member.getName());

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

    // TODO 코드 커지면 적절한 패키지 찾기 => helper?
    private MemberDto findMember(PlaceOrderCommand command) {
        MemberDto member = memberClient.getMember(command.getMemberId());
        if (member == null) {
            throw new RuntimeException("member not found");
        }
        return member;
    }

    private OrderProduct createOrderProduct(OrderProductCommand orderProductCommand) {
        ProductDto product = productClient.getProduct(orderProductCommand.getProductId());
        if (product == null) {
            throw new RuntimeException("product not found");
        }
        return new OrderProduct(product.getId(), product.getPrice(), orderProductCommand.getQuantity());
    }

    private ShippingInfo createShoppingInfo(ShippingInfoCommand request) {
        Address address = new Address(request.getZipCode(), request.getAddress1(), request.getAddress2());
        Receiver receiver = new Receiver(request.getReceiverName(), request.getReceiverPhone());
        return new ShippingInfo(address, request.getMessage(), receiver);
    }
}
