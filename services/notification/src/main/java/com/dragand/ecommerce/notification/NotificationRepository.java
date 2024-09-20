package com.dragand.ecommerce.notification;


import com.dragand.ecommerce.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
