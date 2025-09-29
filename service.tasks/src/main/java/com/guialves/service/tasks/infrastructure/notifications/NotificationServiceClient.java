package com.guialves.service.tasks.infrastructure.notifications;

import com.guialves.service.tasks.application.dtos.NotificationRequest;
import com.guialves.service.tasks.core.ports.NotificationPort;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceClient implements NotificationPort {
    
    private final NotificationClient notificationClient;
    
    public NotificationServiceClient(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }
    
    @Override
    public void sendNotification(NotificationRequest request) {
        notificationClient.sendNotification(request);
    }
}