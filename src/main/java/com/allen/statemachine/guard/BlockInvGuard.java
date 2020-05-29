package com.allen.statemachine.guard;

import com.allen.statemachine.sm.SMEvent;
import com.allen.statemachine.entity.CreateOrder;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component("BlockInvGuard")
public class BlockInvGuard implements Predicate<SMEvent<CreateOrder>> {

    @Override
    public boolean test(SMEvent<CreateOrder> event) {
        CreateOrder createOrder = event.getPayload();
        return createOrder.getQty() <= 5;
    }
}
