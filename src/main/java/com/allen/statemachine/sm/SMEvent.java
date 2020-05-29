package com.allen.statemachine.sm;

import com.allen.statemachine.entity.Order;
import lombok.Data;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Data
public class SMEvent<T> {

    private final String role;

    private final String event;

    private final Order order;

    private final T payload;

    private String targetState;

    public static <T> SMEvent<T> create(String role, String event, Order order, T payload) {
        return new SMEvent<>(role, event, order, payload);
    }

    public long getOrderId() {
        return order.getId();
    }
}
