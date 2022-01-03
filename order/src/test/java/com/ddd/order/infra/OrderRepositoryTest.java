package com.ddd.order.infra;

import com.ddd.order.domain.model.*;
import com.ddd.order.infra.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository repository;

    @Test
    void saveTest() {
        // given
        Orderer orderer = new Orderer(1L, "김이름");

        List<OrderProduct> orderProducts = List.of(
                new OrderProduct(1L, new Money(new BigDecimal(1000)), 1),
                new OrderProduct(2L, new Money(new BigDecimal(2000)), 2),
                new OrderProduct(3L, new Money(new BigDecimal(3000)), 3)
        );

        ShippingInfo shippingInfo = new ShippingInfo(
                new Address("123456", "address1", "address2"),
                "문앞에놔주세요.",
                new Receiver("김이름", "010-1111-1111")
        );

        // when
        OrderEntity orderEntity = new OrderEntity(orderer, orderProducts, shippingInfo);
        repository.save(orderEntity);

        // then
        assertThat(repository.findById(1L).orElseThrow()).isEqualTo(orderEntity);
    }
}