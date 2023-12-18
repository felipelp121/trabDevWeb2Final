package com.trab.devweb.util;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trab.devweb.model.User;
import com.trab.devweb.model.Activity;

@Component 
public class Formatter {
    
    public List<Map<String, Object>> formatUser(List<User> userList) {
        List<Map<String, Object>> resultMapList  = new ArrayList<>();

        for (User user : userList) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("type", user.getType());
            userMap.put("name", user.getName());
            userMap.put("createdAt", user.getCreatedAt());

            resultMapList.add(userMap);
        }

        return resultMapList;
    }

    public List<Map<String, Object>> formatActivityNoDetail(List<Activity> activityList) {
        List<Map<String, Object>> resultMapList  = new ArrayList<>();
    
        for (Activity activity : activityList) {
            Map<String, Object> activityMap = new HashMap<>();
            activityMap.put("id", activity.getId());
            activityMap.put("name", activity.getName());
            activityMap.put("link", activity.getLink());
    
            resultMapList.add(activityMap);
        }
    
        return resultMapList;
    }

    public List<Map<String, Object>> formatActivityWithDetail(List<Activity> activityList) {
        List<Map<String, Object>> resultMapList  = new ArrayList<>();
    
        for (Activity activity : activityList) {
            Map<String, Object> activityMap = new HashMap<>();
            activityMap.put("id", activity.getId());
            activityMap.put("name", activity.getName());
            activityMap.put("type", activity.getType());
            activityMap.put("description", activity.getDescription());
            activityMap.put("date", activity.getDate());
            activityMap.put("start_time", activity.getStartTime());
            activityMap.put("end_time", activity.getEndTime());

    
            resultMapList.add(activityMap);
        }
    
        return resultMapList;
    }
}
