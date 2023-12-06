package com.cg.teamoptimus.WealthManagement.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.teamoptimus.WealthManagement.model.Notification;

@Repository
public interface INotificationRepository extends MongoRepository<Notification, String> {
	
    List<Notification> findNotificationsByUserIdAndStatus(UUID userId, String status);

    @Query(value = "{'userId' : ?0}", sort = "{'notificationDate' : -1}")
    List<Notification> findLatestNotificationsForUserWithLimit(UUID userId, Pageable pageable);
}
