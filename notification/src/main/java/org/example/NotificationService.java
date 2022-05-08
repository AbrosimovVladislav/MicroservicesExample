package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.dto.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveNotification(NotificationRequest request) {
        Notification notification = Notification.builder()
                .toCustomerId(request.getToCustomerId())
                .toCustomerEmail(request.getToCustomerEmail())
                .sender("")
                .message(request.getMessage())
                .sentAt(LocalDateTime.now()).build();
        log.info("Prepare notification to save: {}", notification);
        notificationRepository.saveAndFlush(notification);
        log.info("Notification {} successfully saved", notification);
    }
}
