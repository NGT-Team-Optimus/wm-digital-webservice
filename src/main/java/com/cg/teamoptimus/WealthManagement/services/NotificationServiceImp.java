package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Notification;
import org.springframework.data.domain.Pageable;
import com.cg.teamoptimus.WealthManagement.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationServiceImp implements INotificationService {

    private final INotificationRepository notificationRepo;

    @Autowired
    public NotificationServiceImp(INotificationRepository notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepo.findAll();
    }

    public Optional<Notification> getNotificationById(String id) {
        return notificationRepo.findById(id);
    }

    public List<Notification> getNotificationsByUserIdAndStatus(UUID userId, String status) {
        return notificationRepo.findNotificationsByUserIdAndStatus(userId, status);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepo.save(notification);
    }

    public void deleteNotificationById(String id) {
        notificationRepo.deleteById(id);
    }

    public void sendNotification(String message) {
        System.out.println("Sending notification: " + message);
    }
      
    @Override
    public List<Notification> getLatest12NotificationsForUser(UUID userId) {
        Pageable pageable = PageRequest.of(0, 12); // Limit to 12 results
        return notificationRepo.findLatestNotificationsForUserWithLimit(userId, pageable);
    }

}
