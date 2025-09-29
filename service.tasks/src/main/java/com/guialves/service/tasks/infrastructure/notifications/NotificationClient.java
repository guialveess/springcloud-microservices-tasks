package com.guialves.service.tasks.infrastructure.notifications;

import com.guialves.service.tasks.application.dtos.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "service-notification", url = "${service.notification.url:http://localhost:8082}")
public interface NotificationClient {
    
    @PostMapping("/notification")
    void sendNotification(@RequestBody NotificationRequest request);
}