package com.allen.statemachine.actions;

import com.allen.statemachine.entity.Order;
import com.allen.statemachine.repository.OrderDao;
import com.allen.statemachine.sm.SMAction;
import com.allen.statemachine.sm.SMEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component("default")
@Slf4j
public class ChangeStateAction<T> implements SMAction<T> {

    @Resource
    private OrderDao orderDao;

    @Override
    public void accept(SMEvent<T> event) {
        Order order = event.getOrder();
        acceptInternal(order, event);
        order.setState(event.getTargetState());
        orderDao.update(order);
    }

    protected void acceptInternal(Order order, SMEvent<T> event) {
    }
}
