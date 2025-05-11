package org.example.ragservice.client;

import org.example.userservice.ui.dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "message-service-client", url = "${message.service.url}")
public interface MessageServiceClient {

    @PostMapping("/messages")
    void sendMessage(@RequestBody MessageDTO messageDTO);
}
