package org.example.ragservice.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SearchResultItem {
    private String id;
    private Double score;
    private Map<String, Object> payload;
}
