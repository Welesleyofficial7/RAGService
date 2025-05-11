package org.example.ragservice.client;

import org.example.ragservice.dto.TextInput;
import org.example.ragservice.dto.EmbeddingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "embedding-client", url = "${embedding.model.url}")
public interface EmbeddingClient {

    @PostMapping("/embed_query")
    EmbeddingResponse getQueryEmbedding(@RequestBody TextInput input);
}
