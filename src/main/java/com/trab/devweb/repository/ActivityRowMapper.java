package com.trab.devweb.repository;

import org.springframework.jdbc.core.RowMapper;
import com.trab.devweb.model.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;

class ActivityRowMapper implements RowMapper<Activity> {

    @Override
    public Activity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Activity activity = new Activity();
        activity.setId(resultSet.getInt("id"));
        activity.setEventId(resultSet.getLong("event_id"));
        activity.setType(resultSet.getString("type"));
        activity.setName(resultSet.getString("name"));
        activity.setDescription(resultSet.getString("description"));
        activity.setDate(resultSet.getString("date"));
        activity.setStartTime(resultSet.getString("start_time"));
        activity.setEndTime(resultSet.getString("end_time"));
        activity.setCreatedAt(resultSet.getDate("created_at"));
        return activity;
    }
}
