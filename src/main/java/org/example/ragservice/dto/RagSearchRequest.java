package org.example.ragservice.dto;

import lombok.Data;

@Data
public class RagSearchRequest {
    private String query;
    private Long chatId;
}
