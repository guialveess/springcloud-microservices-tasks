package com.guialves.service.tasks.core.ports;

import com.guialves.service.tasks.application.dtos.NotificationRequest;

public interface NotificationPort {
    void sendNotification(NotificationRequest request);
}