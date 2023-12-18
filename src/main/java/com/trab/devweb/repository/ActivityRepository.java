package com.trab.devweb.repository;

import java.util.List;
import com.trab.devweb.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActivityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Activity> getAllActivities(int eventId) {
        String sql = "SELECT * FROM activity WHERE event_id = " + eventId + ";";
        return jdbcTemplate.query(sql, new ActivityRowMapper());
    }
    public List<Activity> getActivity(int eventId, int activityId) {
        String sql = "SELECT * FROM activity WHERE id = " + activityId + " AND event_id = " + eventId + ";";
        return jdbcTemplate.query(sql, new ActivityRowMapper());
    }

    public void createActivity(Activity activity) {
        try {
            String sql = "INSERT INTO activity (type, name, description, date, start_time, end_time, event_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, activity.getType(), activity.getName(), activity.getDescription(), activity.getDate(), activity.getStartTime(), activity.getEndTime(), activity.getEventId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void likeActivity(int activityId, Long userId) {
        try {
            String sql = "INSERT INTO activity_like (activity_id, user_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, activityId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disLikeActivity(int activityId, Long userId) {
        try {
            String sql = "DELETE FROM activity_like WHERE activity_id = ? AND user_id = ?";
            jdbcTemplate.update(sql, activityId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

