package com.cg.teamoptimus.WealthManagement.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.cg.teamoptimus.WealthManagement.model.Notification;

@Repository
public interface INotificationRepository extends MongoRepository<Notification, String> {
	
    List<Notification> findNotificationsByUserIdAndStatus(UUID userId, String status);

}
