package com.dddstart.order.infra;

import com.ddd.order.command.domain.*;
import com.dddstart.order.command.domain.*;
import com.dddstart.order.infra.repository.OrderRepository;
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
        Orderer orderer = Orderer.create(1L, "김이름");

        List<OrderProduct> orderProducts = List.of(
                OrderProduct.create(1L, new Money(new BigDecimal(1000)), 1),
                OrderProduct.create(2L, new Money(new BigDecimal(2000)), 2),
                OrderProduct.create(3L, new Money(new BigDecimal(3000)), 3)
        );

        ShippingInfo shippingInfo = ShippingInfo.create(
                Address.create("123456", "address1", "address2"),
                "문앞에놔주세요.",
                Receiver.create("김이름", "010-1111-1111")
        );

        // when
        OrderEntity orderEntity = OrderEntity.create(orderer, orderProducts, shippingInfo);
        repository.save(orderEntity);

        // then
        assertThat(repository.findById(1L).orElseThrow()).isEqualTo(orderEntity);
    }
}