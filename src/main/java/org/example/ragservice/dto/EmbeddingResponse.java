package org.example.ragservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddingResponse {
    private List<Double> embedding;
}
