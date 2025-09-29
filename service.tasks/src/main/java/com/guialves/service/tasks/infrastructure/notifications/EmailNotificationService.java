package com.guialves.service.tasks.infrastructure.notifications;

import com.guialves.service.tasks.application.dtos.NotificationRequest;
import com.guialves.service.tasks.core.ports.NotificationPort;
import org.springframework.stereotype.Service;

// @Service - Desativado para usar NotificationServiceClient
public class EmailNotificationService implements NotificationPort {
    @Override
    public void sendNotification(NotificationRequest request) {
        System.out.println("Enviando email para " + request.email() + ": " + request.message());
    }
}
