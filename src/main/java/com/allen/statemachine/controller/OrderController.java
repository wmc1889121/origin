package com.allen.statemachine.controller;

import com.allen.statemachine.entity.CreateOrder;
import com.allen.statemachine.entity.Order;
import com.allen.statemachine.entity.PaySuccess;
import com.allen.statemachine.repository.OrderDao;
import com.allen.statemachine.sm.OrderSM;
import com.allen.statemachine.sm.OrderSMProvider;
import com.allen.statemachine.sm.SMEvent;
import com.allen.statemachine.sm.SMProcessResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author Allen Wan
 * @version 1.0
 */
@RestController
@RequestMapping("/sm/tenant/{tenant}/role/{role}/orders")
public class OrderController {

    @Resource
    private OrderSMProvider orderSMProvider;
    @Resource
    private OrderDao orderDao;

    @PostMapping
    public Object create(@PathVariable("tenant") long tenant, @PathVariable("role") String role,
                         @RequestBody CreateOrder createOrder) throws Exception {

        OrderSM sm = orderSMProvider.getStateMachine(tenant);

        Order order = Order.create();
        order.setTenantId(tenant);

        sm.onEvent(null, SMEvent.create(role, "create", order, createOrder));

        return Collections.singletonMap("id", order.getId());
    }

    @PostMapping("/{id}/event/{event}")
    public Object event(@PathVariable("tenant") long tenant, @PathVariable("role") String role,
                        @PathVariable("id") long id, @PathVariable("event") String event) throws Exception {

        Order order = orderDao.get(id);

        if (order == null) {
            throw new IllegalArgumentException("order does not exist");
        }

        OrderSM sm = orderSMProvider.getStateMachine(tenant);

        SMProcessResult result = sm.onEvent(order.getState(), SMEvent.create(role, event, order, null));

        return Collections.singletonMap("state", result.getNewState());
    }

    @PutMapping("/{id}/pay_success")
    public Object paySuccess(@PathVariable("tenant") long tenant, @PathVariable("role") String role,
                             @PathVariable("id") long id, @RequestBody PaySuccess paySuccess) throws Exception {

        Order order = orderDao.get(id);

        if (order == null) {
            throw new IllegalArgumentException("order does not exist");
        }

        OrderSM sm = orderSMProvider.getStateMachine(tenant);

        SMProcessResult result = sm.onEvent(order.getState(), SMEvent.create(role, "pay_success", order, paySuccess));

        return Collections.singletonMap("state", result.getNewState());
    }
}
