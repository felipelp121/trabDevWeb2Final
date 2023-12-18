package com.trab.devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;
import com.trab.devweb.util.Formatter;
import com.trab.devweb.service.ActivityService;
import com.trab.devweb.model.Activity;
import com.trab.devweb.model.User;

@RestController
@RequestMapping("/api/event/{eventId}/activity")
public class ActivityController {

    private final ActivityService activityService;
    private final Formatter formatter;

    @Autowired
    public ActivityController(ActivityService activityService, Formatter formatter) {
        this.activityService = activityService;
        this.formatter = formatter;
    }

    @GetMapping
    public List<Map<String, Object>> getAllActivities(@PathVariable int eventId) {
        List<Activity> activityList = activityService.getAllActivities(eventId);
        return formatter.formatActivityNoDetail(activityList);
    }

    @GetMapping("/{activityId}")
    public List<Map<String, Object>> getActivity(@PathVariable int eventId, @PathVariable int activityId) {
        List<Activity> activityList = activityService.getActivity(eventId, activityId);
        return formatter.formatActivityWithDetail(activityList);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createActivity(@RequestBody Activity activity) {
        if (activity.getType() == null || activity.getName() == null 
        || activity.getDescription() == null || activity.getDate() == null
        || activity.getStartTime() == null || activity.getEndTime() == null
        || activity.getEventId() == null) {
            return new ResponseEntity<>("Campos obrigatórios não preenchidos", HttpStatus.BAD_REQUEST);
        }

        activityService.createActivity(activity);
        return new ResponseEntity<>("Campos obrigatórios preenchidos", HttpStatus.ACCEPTED);
    }

    @PostMapping("/{activityId}/like")
    public ResponseEntity<String> likeActivity(@PathVariable int activityId, @RequestBody User user) {
        if (activityId == 0 || user.getId() == 0) {
            return new ResponseEntity<>("Campos obrigatórios não preenchidos", HttpStatus.BAD_REQUEST);
        }

        activityService.likeActivity(activityId, user.getId());
        return new ResponseEntity<>("Você curtiu essa atividade", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{activityId}/dislike")
    public ResponseEntity<String> disLikeActivity(@PathVariable int activityId, @RequestBody User user) {
        if (activityId == 0 || user.getId() == 0) {
            return new ResponseEntity<>("Campos obrigatórios não preenchidos", HttpStatus.BAD_REQUEST);
        }

        activityService.disLikeActivity(activityId, user.getId());
        return new ResponseEntity<>("Você descurtiu essa atividade", HttpStatus.ACCEPTED);
    }


}


