package com.allen.statemachine.repository;

import com.allen.statemachine.entity.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Repository
public class OrderDao {
    private static final RowMapper<Order> MAPPER = new BeanPropertyRowMapper<>(Order.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    public Order get(long id) {
        String sql = "select * from tm_order where id = ?";

        List<Order> list = jdbcTemplate.query(sql, new Object[]{id}, MAPPER);

        return list.isEmpty() ? null : list.get(0);
    }

    public long insert(Order order) {

        String sql = "insert into tm_order(id, tenant_id, state, pay_order_id, pay_channel, pay_time, delivery_id, " +
                "delivery_time, refund_order_id, refund_time, create_time) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[]{order.getId(), order.getTenantId(), order.getState(),
                order.getPayOrderId(), order.getPayChannel(), order.getPayTime(), order.getDeliveryId(),
                order.getDeliveryTime(), order.getRefundOrderId(), order.getRefundTime(), order.getCreateTime()});

        return order.getId();
    }

    public long update(Order order) {

        String sql = "update tm_order set tenant_id = ?, state = ?, pay_order_id = ?, pay_channel = ?, pay_time = ?, " +
                "delivery_id = ?, delivery_time = ?, refund_order_id = ?, refund_time = ?, create_time = ? where id = ?";

        jdbcTemplate.update(sql, new Object[]{order.getTenantId(), order.getState(),
                order.getPayOrderId(), order.getPayChannel(), order.getPayTime(), order.getDeliveryId(), order.getDeliveryTime(),
                order.getRefundOrderId(), order.getRefundTime(), order.getCreateTime(), order.getId()});

        return order.getId();
    }
}
