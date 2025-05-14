package org.example.ragservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QdrantSearchResponse {
    @JsonProperty("result")
    private List<SearchResultItem> points;

    // Getters and setters
    public List<SearchResultItem> getPoints() {
        return points;
    }

    public void setPoints(List<SearchResultItem> points) {
        this.points = points;
    }
}
