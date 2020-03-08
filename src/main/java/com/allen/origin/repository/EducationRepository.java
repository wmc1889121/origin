package com.allen.origin.repository;

import com.allen.origin.entity.domain.Education;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class EducationRepository {
    private static final RowMapper<Education> MAPPER = new BeanPropertyRowMapper<>(Education.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Education> selectByUser(long userId) {
        String sql = "select * from tm_education where user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, MAPPER);
    }
}
