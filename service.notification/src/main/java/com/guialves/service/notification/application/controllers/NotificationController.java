package com.guialves.service.notification.application.controllers;

import com.guialves.service.notification.application.controllers.dtos.NotificationRequest;
import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody NotificationRequest request) {
        System.out.println(request.message());
        return ResponseEntity.ok().build();
    }
}
