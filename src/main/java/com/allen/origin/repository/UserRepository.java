package com.allen.origin.repository;

import com.allen.origin.entity.domain.Location;
import com.allen.origin.entity.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Repository
public class UserRepository {
    private static final RowMapper<UserPO> MAPPER = new BeanPropertyRowMapper<>(UserPO.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    public User select(long id) {
        String sql = "select * from tm_user where id = ? and is_deleted = 0";
        return jdbcTemplate.query(sql, new Object[]{id}, MAPPER)
                .stream().findFirst().orElse(null);
    }

    public int delete(long id) {
        String sql = "update tm_user set is_deleted = 1 where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Getter
    @Setter
    public static class UserPO extends User {
        private int locationId;
        private String locationName;

        @Override
        public Location getLocation() {
            if (super.getLocation().getId() == null) {
                Location location = new Location();
                location.setId(locationId);
                location.setName(locationName);
                setLocation(location);
            }
            return super.getLocation();
        }
    }
}
