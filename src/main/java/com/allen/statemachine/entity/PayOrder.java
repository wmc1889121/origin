package com.allen.statemachine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayOrder {
    private String channel;
    private long amount;
}
