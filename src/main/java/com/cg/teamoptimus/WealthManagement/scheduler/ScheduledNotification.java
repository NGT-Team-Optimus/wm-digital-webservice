package com.cg.teamoptimus.WealthManagement.scheduler;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.Notification;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.model.UserGoal;
import com.cg.teamoptimus.WealthManagement.repository.INotificationRepository;
import com.cg.teamoptimus.WealthManagement.repository.IUserGoalRepository;
import com.cg.teamoptimus.WealthManagement.services.NotificationServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledNotification {

    private static final Logger log = LoggerFactory.getLogger(ScheduledNotification.class);

    private final IUserGoalRepository userGoalRepository;
    private final INotificationRepository notificationRepository;
    private final NotificationServiceImp notificationService;

    @Autowired
    public ScheduledNotification(IUserGoalRepository userGoalRepository, INotificationRepository notificationRepository, NotificationServiceImp notificationService) {
        this.userGoalRepository = userGoalRepository;
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
    }
//    @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(fixedRate = 3600000) // Runs every hour.
    public void checkAndCreateNotifications() {
        List<UserGoal> userGoals = userGoalRepository.findAll();

        for (UserGoal userGoal : userGoals) {
            List<Goal> goals = userGoal.getGoals();
            for (Goal goal : goals) {
                if ("completed".equalsIgnoreCase(goal.getStatus())) {
                    continue;
                }

                Long daysRemaining = calculateDaysRemaining(goal.getDuration());
                if (daysRemaining <= 10 && daysRemaining != null) {
                    createNotification(goal, userGoal.getUser());
                }
            }
        }
    }


    private Long calculateDaysRemaining(Date duration) {
        if (duration != null) {
            Date currentDate = new Date();
            long durationInMillis = duration.getTime() - currentDate.getTime();
            return Duration.ofMillis(durationInMillis).toDays();
        }
        return null;
    }


    private void createNotification(Goal goal, User user) {
        Notification notification = new Notification();
        notification.setUserId(user.getUserId());
        notification.setUserName(user.getUsername());
        notification.setGoalId(goal.getGoalId());
        notification.setGoalName(goal.getGoalName());
        
        LocalDateTime notificationDateTime = LocalDateTime.now(ZoneId.systemDefault()); // Get current date and time
        notification.setNotificationDate(notificationDateTime);
        
//        LocalDate notificationDate = LocalDate.now(); // Assign the current system date to notificationDate
//        notification.setNotificationDate(notificationDate);
        
        long daysRemaining = calculateDaysRemaining(goal.getDuration());
        if (daysRemaining > 0) {
            notification.setTimeline(daysRemaining + " Days Left");
            System.out.println("Hi "+user.getUsername()+", "+daysRemaining+" Days left to complete your "+goal.getGoalName()+" Goal." + notificationDateTime);
        } else {
            notification.setTimeline("Time End");
        }
        
        
//        notification.setStatus(goal.getStatus());
        notificationRepository.save(notification);
    }
    
    
    
    
}
	

