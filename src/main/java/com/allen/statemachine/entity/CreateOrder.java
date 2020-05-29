package com.allen.statemachine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder {
    private long orderId;
    private long price;
    private long productId;
    private int qty;
}
