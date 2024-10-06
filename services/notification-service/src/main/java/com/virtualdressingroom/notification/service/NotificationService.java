package com.virtualdressingroom.notification.service;

import com.virtualdressingroom.notification.dtos.EmailRequest;
import com.virtualdressingroom.notification.dtos.SmsRequest;

public interface NotificationService {
    void sendEmail(EmailRequest request);

    void sendSms(SmsRequest request);
}
