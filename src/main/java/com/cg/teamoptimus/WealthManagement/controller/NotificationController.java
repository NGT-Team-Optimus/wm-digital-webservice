package com.cg.teamoptimus.WealthManagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.teamoptimus.WealthManagement.model.Notification;
import com.cg.teamoptimus.WealthManagement.services.INotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	    private final INotificationService notificationService;

	    @Autowired
	    public NotificationController(INotificationService notificationService) {
	        this.notificationService = notificationService;
	    }

	    @GetMapping("/user/{userId}/latest")
	    public ResponseEntity<List<Notification>> getLatestNotificationsForUser(@PathVariable UUID userId) {
	        List<Notification> latestNotifications = notificationService.getLatest12NotificationsForUser(userId);

	        if (latestNotifications.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }

	        return new ResponseEntity<>(latestNotifications, HttpStatus.OK);
	    }

}
