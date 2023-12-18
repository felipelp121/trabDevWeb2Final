package com.trab.devweb.repository;

import org.springframework.jdbc.core.RowMapper;
import com.trab.devweb.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setType(resultSet.getInt("type"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        user.setCreatedAt(resultSet.getDate("created_at"));
        return user;
    }
}
