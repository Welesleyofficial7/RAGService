package org.example.ragservice.service;

import feign.FeignException;
import org.example.ragservice.client.*;
import lombok.RequiredArgsConstructor;
import org.example.ragservice.dto.EmbeddingResponse;
import org.example.ragservice.dto.QdrantSearchResponse;
import org.example.ragservice.dto.SearchResultItem;
import org.example.ragservice.dto.TextInput;
import org.example.userservice.domain.model.Enum.Role;
import org.example.userservice.ui.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RagService {

    private final EmbeddingClient embeddingClient;
    private final QdrantClient qdrantClient;
    private final LlmClient llmClient;
    private final MessageServiceClient messageServiceClient;

    public String searchInQdrant(String userQuery) {
        try {
            TextInput input = new TextInput();
            input.setText(userQuery);
            EmbeddingResponse embedding = embeddingClient.getQueryEmbedding(input);

            Map<String, Object> searchRequest = new HashMap<>();
            searchRequest.put("vector", embedding.getEmbedding());
            searchRequest.put("limit", 3);

            QdrantSearchResponse response = qdrantClient.searchPoints("knowledge_point", searchRequest);
            List<SearchResultItem> results = Optional.ofNullable(response.getPoints()).orElse(Collections.emptyList());

            StringBuilder context = new StringBuilder();
            for (SearchResultItem result : results) {
                Map<String, Object> payload = result.getPayload();
                if (payload != null && payload.containsKey("text")) {
                    context.append(payload.get("text")).append("\n");
                }
            }

            return context.toString();

        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Коллекция knowledge_points не найдена в Qdrant", e);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка поиска в Qdrant: " + e.getMessage(), e);
        }
    }

    public void handleUserQuery(String userQuery, Long chatId) {
        try {
            String context = searchInQdrant(userQuery);

            Map<String, String> llmRequest = new HashMap<>();
            llmRequest.put("query", userQuery);
            llmRequest.put("context", context);

            String llmAnswer = llmClient.processWithRag(llmRequest);

            MessageDTO botMessage = new MessageDTO(null, llmAnswer, Role.BOT, chatId);
            messageServiceClient.sendMessage(botMessage);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка обработки RAG-запроса: " + e.getMessage(), e);
        }
    }
}
