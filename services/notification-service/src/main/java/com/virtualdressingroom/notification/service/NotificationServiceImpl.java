package com.virtualdressingroom.notification.service;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.virtualdressingroom.notification.dtos.NotificationRequest;
import com.virtualdressingroom.notification.exception.NotificationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    //private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final JavaMailSender javaMailSender;
    private final TwilioRestClient twilioRestClient;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Async
    @Override
    public void sendEmail(NotificationRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("your-email@gmail.com");
            message.setTo(request.to());
            message.setSubject(request.subject());
            message.setText(request.message());
            javaMailSender.send(message);
            log.info("Email sent to {}", request.to());
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage());
            throw new NotificationException("Failed to send email: " + e.getMessage());
        }
    }

    @Async
    @Override
    public void sendSms(NotificationRequest request) {
        try {
            Message.creator(
                    new PhoneNumber(request.to()), // To number
                    new PhoneNumber(twilioPhoneNumber), // From Twilio number
                    request.message()
            ).create(twilioRestClient);
            log.info("SMS sent to {}", request.to());
        } catch (Exception e) {
            log.error("Failed to send SMS: {}", e.getMessage());
            throw new NotificationException("Failed to send SMS: " + e.getMessage());
        }
    }
}

