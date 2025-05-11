package org.example.ragservice.client;

import org.example.ragservice.dto.QdrantSearchResponse;
import org.example.ragservice.dto.SearchResultItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "qdrant-client", url = "${qdrant.db.url}")
public interface QdrantClient {

    @PostMapping("/collections/{collectionName}/points/search")
    QdrantSearchResponse searchPoints(@PathVariable String collectionName, @RequestBody Map<String, Object> request);
}
