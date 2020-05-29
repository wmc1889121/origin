package com.allen.statemachine.actions;

import com.allen.statemachine.entity.Order;
import com.allen.statemachine.sm.SMEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component("ApplyRefundAction")
@Slf4j
public class ApplyRefundAction extends ChangeStateAction<Void> {
    private static final AtomicLong idGenerator = new AtomicLong();

    @Override
    protected void acceptInternal(Order order, SMEvent<Void> event) {
        long refundOrderId = createRefundOrder(event);

        order.setRefundOrderId(refundOrderId);
        order.setRefundTime(new Date());
    }

    private long createRefundOrder(SMEvent<Void> event) {
        log.info("申请退款：order: {}", event.getOrderId());

        return idGenerator.getAndIncrement();
    }
}
