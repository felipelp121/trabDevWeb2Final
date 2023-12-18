package com.trab.devweb.repository;

import java.util.List;
import com.trab.devweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public void createUser(User user) {
        try {
            String sql = "INSERT INTO user (type, name, password) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, user.getType(), user.getName(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

