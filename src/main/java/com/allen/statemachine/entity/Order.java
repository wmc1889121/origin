package com.allen.statemachine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class Order {
    private static final AtomicLong idGenerator = new AtomicLong();

    private long id;
    private long tenantId;
    private String state;
    private long payOrderId;
    private String payChannel;
    private Date payTime;
    private long deliveryId;
    private Date deliveryTime;
    private long refundOrderId;
    private Date refundTime;
    private Date createTime;

    public static Order create() {
        return new Order(idGenerator.getAndIncrement());
    }

    public Order(long id) {
        this.id = id;
    }
}
