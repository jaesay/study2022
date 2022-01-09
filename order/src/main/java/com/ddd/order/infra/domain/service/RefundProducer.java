package com.ddd.order.infra.domain.service;

import com.ddd.order.domain.event.OrderCanceledEvent;
import com.ddd.order.domain.service.RefundService;
import com.ddd.order.domain.support.annotation.DomainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@DomainService
@Slf4j
public class RefundProducer implements RefundService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value(value = "${message.topic.name}")
    private String topicName;

    public RefundProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void refund(long orderId) {
        String message = "";
        try {
            message = objectMapper.writeValueAsString(OrderCanceledEvent.create(orderId));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        kafkaTemplate.send(topicName, message).addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.debug(result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }
        });
    }
}
