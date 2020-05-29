package com.allen.statemachine.sm;

/**
 * @author Allen Wan
 * @version 1.0
 */
public enum OrderState {

    created("已创建"),
    processing("进行中"),
    paying("支付中"),
    wait_delivery("待发货"),
    delivering("已发货"),
    wait_receipt("待签收"),
    success("交易完成"),
    refunding("退款中"),
    refund_auth("退款待审核"),
    closed("交易关闭");

    private String remark;

    OrderState(String remark) {
        this.remark = remark;
    }
}
