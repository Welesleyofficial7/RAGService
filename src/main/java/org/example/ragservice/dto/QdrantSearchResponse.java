package org.example.ragservice.dto;

import java.util.List;

public class QdrantSearchResponse {
    private List<SearchResultItem> points;

    // Getters and setters
    public List<SearchResultItem> getPoints() {
        return points;
    }

    public void setPoints(List<SearchResultItem> points) {
        this.points = points;
    }
}
