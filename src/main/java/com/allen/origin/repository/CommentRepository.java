package com.allen.origin.repository;

import com.allen.origin.entity.domain.Comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CommentRepository {
    private static final RowMapper<Comment> MAPPER = new BeanPropertyRowMapper<>(Comment.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Comment> selectByUser(long userId) {
        String sql = "select * from tm_comment where user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, MAPPER);
    }
}
