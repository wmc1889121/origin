//package com.allen.origin.service;
//
//import com.example.demo.order.common.CreatePayment;
//import com.example.demo.order.common.Order;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service
//public class OrderService {
//    private static final RowMapper<Order> MAPPER = new BeanPropertyRowMapper<>(Order.class);
//
//    @Resource
//    private JdbcTemplate jdbcTemplate;
//
//    @Transactional
//    public long create(Order order) {
//        String sql = "insert into tm_order(id, state, amount, pay_time, create_time) " +
//                "values(?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, order.getId(), order.getState().name(),
//                order.getAmount(), order.getPayTime(), order.getCreateTime());
//
//        CreatePayment payment = new CreatePayment();
//        payment.setAmount(order.getAmount());
//        payment.setChannel("alipay");
//        payment.setId(order.getId());
//        return order.getId();
//    }
//
//    @Transactional
//    public boolean updateState(long id, Order.State state) {
//        String sql = "update tm_order set state = ? where id = ? ";
//        return jdbcTemplate.update(sql, state.name(), id) > 0;
//    }
//
//    @Transactional
//    public boolean paid(long id, long payTime) {
//        String sql = "update tm_order set state = ?, pay_time = ? where id = ? ";
//        return jdbcTemplate.update(sql, Order.State.PROCESS.name(), payTime, id) > 0;
//    }
//
//    public Order findById(long id) {
//        String sql = "select * from 'order where id = ?";
//        List<Order> result = jdbcTemplate.query(sql, new Object[]{id}, MAPPER);
//        return result.isEmpty() ? null : result.get(0);
//    }
//}
