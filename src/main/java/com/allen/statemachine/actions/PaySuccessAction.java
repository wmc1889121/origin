package com.allen.statemachine.actions;

import com.allen.statemachine.entity.Order;
import com.allen.statemachine.entity.PaySuccess;
import com.allen.statemachine.sm.SMEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Component("PaySuccessAction")
@Slf4j
public class PaySuccessAction extends ChangeStateAction<PaySuccess> {

    @Override
    protected void acceptInternal(Order order, SMEvent<PaySuccess> event) {
        log.info("支付结果：{}", event.getPayload());

        PaySuccess paySuccess = event.getPayload();
        order.setPayOrderId(paySuccess.getTradeNo());
        order.setPayTime(paySuccess.getPayTime());
        order.setPayChannel(paySuccess.getChannel());
    }
}
