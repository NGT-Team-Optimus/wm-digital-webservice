package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Notification;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface INotificationService {


	    List<Notification> getAllNotifications();

	    Optional<Notification> getNotificationById(String id);

	    List<Notification> getNotificationsByUserIdAndStatus(UUID userId, String status);

	    Notification saveNotification(Notification notification);

	    void deleteNotificationById(String id);


}
