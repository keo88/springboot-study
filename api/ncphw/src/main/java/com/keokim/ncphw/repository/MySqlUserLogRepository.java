package com.keokim.ncphw.repository;

import com.keokim.ncphw.domain.LogMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

@Repository
public class MySqlUserLogRepository implements UserLogRepository {

    private final JdbcTemplate jdbcTemplate;

    public MySqlUserLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LogMessage save(LogMessage message) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "insert into log_message (user_id, message) values (?, ?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"log_id"});
            ps.setLong(1, message.getUserId());
            ps.setString(2, message.getMessage());
            return ps;
        }, keyHolder);

        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        message.setLogId(id);

        return message;
    }
}
