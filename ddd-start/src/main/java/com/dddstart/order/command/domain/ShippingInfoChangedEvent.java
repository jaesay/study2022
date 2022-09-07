package com.dddstart.order.command.domain;

import lombok.Getter;

import java.time.LocalDateTime;

// 클래스 이름으로 이벤트 종류를 표현
// 이벤트 발생 시간
// 추가 데이터: 주문번호, 신규 배송지 정보 등 이벤트와 관련된 정보
@Getter
public class ShippingInfoChangedEvent {
    private long orderId; // 추가데이터
    private LocalDateTime createdAt; // 이벤트 발생시간
    private ShippingInfo newShippingInfo; // 추가 데이터

    public ShippingInfoChangedEvent() {}

    public static ShippingInfoChangedEvent create(long orderId, ShippingInfo shippingInfo) {
        ShippingInfoChangedEvent event = new ShippingInfoChangedEvent();
        event.orderId = orderId;
        event.createdAt = LocalDateTime.now();
        event.newShippingInfo = shippingInfo;
        return event;
    }
}
