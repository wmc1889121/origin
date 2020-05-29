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
public class PaySuccess {
    private long tradeNo;
    private String channel;
    private Date payTime;
    private long amount;
}
