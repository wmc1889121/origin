package com.allen.statemachine.actions;

import com.allen.statemachine.entity.CreateOrder;
import com.allen.statemachine.entity.Order;
import com.allen.statemachine.repository.OrderDao;
import com.allen.statemachine.sm.SMAction;
import com.allen.statemachine.sm.SMEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component("CreateOrderAction")
@Slf4j
public class CreateOrderAction implements SMAction<CreateOrder> {
    @Resource
    private OrderDao orderDao;

    @Override
    public void accept(SMEvent<CreateOrder> event) {
        log.info("创建订单：{}", event.getPayload());
        event.getOrder().setCreateTime(new Date());

        Order order = event.getOrder();
        order.setState(event.getTargetState());

        orderDao.insert(order);
    }
}
