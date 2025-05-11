package org.example.ragservice.controller;

import org.example.ragservice.service.RagService;
import org.example.ragservice.dto.RagSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/rag/process")
    public Map<String, String> processQuery(@RequestBody RagSearchRequest request) {
        String result = ragService.searchInQdrant(request.getQuery());
        return Collections.singletonMap("result", result);
    }

    @PostMapping("/rag/chat")
    public void handleUserQuery(@RequestBody RagSearchRequest request) {
        ragService.handleUserQuery(request.getQuery(), request.getChatId());
    }
}
