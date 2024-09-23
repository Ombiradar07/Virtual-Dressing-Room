package com.virtualdressingroom.notification.controller;

import com.virtualdressingroom.notification.dtos.NotificationRequest;
import com.virtualdressingroom.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {


    private final NotificationService notificationService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody NotificationRequest request) {
        notificationService.sendEmail(request);
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSms(@RequestBody NotificationRequest request) {
        notificationService.sendSms(request);
        return ResponseEntity.ok("SMS sent successfully");
    }
}
