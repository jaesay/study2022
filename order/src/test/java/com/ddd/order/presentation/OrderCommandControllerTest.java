package com.ddd.order.presentation;

import com.ddd.order.application.command.PlaceOrderCommand;
import com.ddd.order.application.command.PlaceOrderCommand.OrderProductCommand;
import com.ddd.order.application.command.PlaceOrderCommand.ShippingInfoCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OrderCommandControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void placeOrderTest() throws Exception {
        // given
        OrderProductCommand orderProductCommand1 = OrderProductCommand.builder()
                .productId(1L).quantity(1).build();

        OrderProductCommand orderProductCommand2 = OrderProductCommand.builder()
                .productId(2L).quantity(2).build();

        ShippingInfoCommand shippingInfoCommand = ShippingInfoCommand.builder()
                .zipCode("123456").address1("address1").address2("address2").message("문앞에놔주세요.").receiverName("김수신자").receiverPhone("01011111111").build();

        PlaceOrderCommand placeOrderCommand = PlaceOrderCommand.builder()
                .memberId(1L).orderProductCommands(List.of(orderProductCommand1, orderProductCommand2)).shippingInfoCommand(shippingInfoCommand).build();

        // when
        ResultActions actions = mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON_VALUE) // 이 요청의 본문에 JSON을 담아서 보냄
                .accept(MediaType.APPLICATION_JSON_VALUE) // JSON 응답을 받고 싶다.
                .content(objectMapper.writeValueAsString(placeOrderCommand)));

        // then
        actions.andDo(print()).andExpect(status().isOk());
    }
}