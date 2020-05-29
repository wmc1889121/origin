package com.allen.statemachine.sm;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Slf4j
public class OrderSM implements StateMachine {
    @Getter
    private final long tenant;
    private Map<String /* src + event */, SMTransaction> transactions;

    public OrderSM(long tenantId) {
        this.tenant = tenantId;
        this.transactions = new HashMap<>();
    }

    @Override
    public SMProcessResult onEvent(String state, SMEvent event) {
        Objects.requireNonNull(event);

        String key = getKey(state, event.getEvent());

        SMTransaction smTransaction = transactions.get(key);

        SMProcessResult res;

        if (smTransaction == null) {
            log.warn("当前状态[{}]拒绝执行event[{}]", state, event.getEvent());
            res = new SMProcessResult(state);// todo 拒绝
        } else {
            res = smTransaction.process(event);
        }

        log.info("order[{}], [{}] -{}-> [{}]", event.getOrderId(), state, event.getEvent(), res.getNewState());

        return res;
    }

    @Override
    public DefaultSMTransaction withTransaction() {
        return new DefaultSMTransaction(this);
    }

    @Override
    public ChoiceSMTransaction withChoice() {
        return new ChoiceSMTransaction(this);
    }

    @Override
    public void addTransaction(SMTransaction transaction) {
        String key = getKey(transaction.getSrc(), transaction.getEvent());
        SMTransaction prev = transactions.put(key, transaction);

        if (prev != null) {
            throw new IllegalArgumentException("已存在[" + key + "]!");
        }
    }

    private String getKey(String src, String event) {
        return src + ":" + event;
    }
}
