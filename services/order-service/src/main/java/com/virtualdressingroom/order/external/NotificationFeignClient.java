package com.virtualdressingroom.order.external;


import com.virtualdressingroom.order.dtos.externalDTOs.EmailRequest;
import com.virtualdressingroom.order.dtos.externalDTOs.SmsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationFeignClient {

    @PostMapping("/api/v1/notifications/email")
    void sendEmail(@RequestBody EmailRequest request);

    @PostMapping("/api/v1/notifications/sms")
    void sendSms(@RequestBody SmsRequest request);
}
