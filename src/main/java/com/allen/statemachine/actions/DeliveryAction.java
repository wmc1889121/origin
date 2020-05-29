package com.allen.statemachine.actions;

import com.allen.statemachine.sm.SMEvent;
import com.allen.statemachine.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component("DeliveryAction")
@Slf4j
public class DeliveryAction extends ChangeStateAction<Void> {
    private static final AtomicLong idGenerator = new AtomicLong();

    @Override
    protected void acceptInternal(Order order, SMEvent<Void> event) {
        long refundOrderId = createDeliveryOrder(event);

        order.setDeliveryId(refundOrderId);
        order.setDeliveryTime(new Date());
    }

    private long createDeliveryOrder(SMEvent<Void> event) {
        log.info("发货：order: {}", event.getOrderId());

        return idGenerator.getAndIncrement();
    }
}
