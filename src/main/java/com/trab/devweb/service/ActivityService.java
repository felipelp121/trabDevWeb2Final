package com.trab.devweb.service;

import com.trab.devweb.repository.ActivityRepository;
import com.trab.devweb.model.Activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAllActivities(int eventId) {
        return activityRepository.getAllActivities(eventId);
    }

    public List<Activity> getActivity(int eventId, int activityId) {
        return activityRepository.getActivity(eventId, activityId);
    }

    public void createActivity(Activity activity) {
        try {
            activityRepository.createActivity(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void likeActivity(int activityId, Long userId) {
        try {
            activityRepository.likeActivity(activityId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disLikeActivity(int activityId, Long userId) {
        try {
            activityRepository.disLikeActivity(activityId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



