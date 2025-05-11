package org.example.ragservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "llm-client", url = "${llm.service.url}")
public interface LlmClient {

    @PostMapping("/rag-process")
    String processWithRag(@RequestBody Map<String, String> request);
}
