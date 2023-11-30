package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Notification;
import com.cg.teamoptimus.WealthManagement.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
