package org.example.clients;

import org.example.clients.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification")
public interface NotificationClient {
    @PostMapping(path = "api/v1/notification")
    void notification(@RequestBody NotificationRequest request);
}
