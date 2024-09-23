package com.virtualdressingroom.notification.service;

import com.virtualdressingroom.notification.dtos.NotificationRequest;

public interface NotificationService {
    void sendEmail(NotificationRequest request);

    void sendSms(NotificationRequest request);
}
