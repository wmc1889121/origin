package com.allen.statemachine.repository;

import com.allen.statemachine.sm.SMConfigurer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @author Allen Wan
 * @version 1.0
 */
@Repository
public class SMConfigurerDao {
    private static final RowMapper<SMConfigurer> MAPPER = new BeanPropertyRowMapper<>(SMConfigurer.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<SMConfigurer> getByTenant(long tenant) {
        String sql = "select * from tm_sm_configurer where tenant_id = ? and pid = -1";
        return jdbcTemplate.query(sql, new Object[]{tenant}, MAPPER);
    }

    public List<SMConfigurer> getByPid(long pid) {
        String sql = "select * from tm_sm_configurer where pid = ?";
        return jdbcTemplate.query(sql, new Object[]{pid}, MAPPER);
    }

    public long insert(SMConfigurer c) {
        String sql = "insert into tm_sm_configurer(tenant_id, pid, type, role, src, event, target, action, guard) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, c.getTenantId());
            ps.setObject(2, c.getPid());
            ps.setObject(3, c.getType());
            ps.setObject(4, c.getRole());
            ps.setObject(5, c.getSrc());
            ps.setObject(6, c.getEvent());
            ps.setObject(7, c.getTarget());
            ps.setObject(8, c.getAction());
            ps.setObject(9, c.getGuard());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void deleteByTenant(long tenant) {
        String sql = "delete from tm_sm_configurer where tenant_id = " + tenant;
        jdbcTemplate.update(sql);
    }
}
